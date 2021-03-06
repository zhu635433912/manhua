package com.zhuyunjian.manhua.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuyunjian.library.StartUtil;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.adapter.HeadAdapter;
import com.zhuyunjian.manhua.download.DownLoadMultipleService;
import com.zhuyunjian.manhua.entity.UrlEntity;
import com.zhuyunjian.manhua.function.RefreshEntity;
import com.zhuyunjian.manhua.service.DownloadService;
import com.zhuyunjian.manhua.utils.FragmentUtil;
import com.zhuyunjian.manhua.utils.RefreshUtil;
import com.zhuyunjian.manhua.utils.SpinnerData;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_heavy)
public class HeavyFragment extends BaseFragment implements View.OnClickListener, ServiceConnection {
    @ViewById(R.id.head_pop)
    LinearLayout linearLayout;
    private PopupWindow popWindow;
    @ViewById(R.id.head_pop_image)
    ImageView titleImage;
    @ViewById(R.id.head_refresh_image)
    ImageView refreshImage;
    @ViewById(R.id.head_offline_btn)
    ImageView offlineImage;
    private  ListView listView;
    private String tag ;
    private String days;
    private String type;
    private UrlEntity urlEntity;
    private List<Map<String,Object>> data ;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentUtil fragmentUtil;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
       tag = getArguments().getString(AppConstants.FRAG_TYPE_ID);
    }

    @Override
    public void intiView() {
        data = new SpinnerData().getHeavyData();
        for (int i = 0; i < SpinnerData.heavys.length; i++) {
            if (tag == "gif"){
                fragments.add(GifFragment_.builder().arg(SpinnerData.TAG,tag)
                        .arg(SpinnerData.DAYS, (String) new SpinnerData().getHeavyData().get(i).get(SpinnerData.DAYS))
                        .arg(SpinnerData.TYPE, (String) new SpinnerData().getHeavyData().get(i).get(SpinnerData.TYPE)).build());

            }else {
                fragments.add(RecentFragment_.builder().arg(SpinnerData.TAG,tag)
                    .arg(SpinnerData.DAYS, (String) new SpinnerData().getHeavyData().get(i).get(SpinnerData.DAYS))
                    .arg(SpinnerData.TYPE, (String) new SpinnerData().getHeavyData().get(i).get(SpinnerData.TYPE)).build());
            }
        }

        fragmentUtil = new FragmentUtil(fragments, getChildFragmentManager(), days, R.id.heavy_container);
        fragmentUtil.show(0);
        showWindow();
        linearLayout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "dianlemei", Toast.LENGTH_SHORT).show();
        if (popWindow.isShowing()){
            popWindow.dismiss();
        }else {
            popWindow.showAsDropDown(linearLayout);
        }
    }

    private void showWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.head_pop_list_layout,null);
        listView = (ListView) view.findViewById(R.id.head_pop_list);

        popWindow = new PopupWindow(view);
        popWindow.setFocusable(true);
        HeadAdapter adapter = new HeadAdapter(data,getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                titleImage.setImageResource(SpinnerData.heavys[position]);
                days = (String) new SpinnerData().getHeavyData().get(position).get(SpinnerData.DAYS);
                popWindow.dismiss();
                fragmentUtil.show(position);
            }
        });
        listView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        popWindow.setWidth(listView.getMeasuredWidth());
        popWindow.setHeight((listView.getMeasuredHeight())*3+10);
        popWindow.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.color.spinner_item));
        popWindow.setOutsideTouchable(true);
    }

    @Override
    public void initData() {
        offlineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "下载中", Toast.LENGTH_SHORT).show();
                getContext().startService(new Intent(getContext(),DownloadService.class));
                EventBus.getDefault().post(urlEntity,AppConstants.REFRESH_TAG_TO);
            }
        });
//        RefreshUtil.refreshView(refreshImage);
        refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshImage.setImageResource(R.mipmap.ic_refresh_pressed);
                EventBus.getDefault().post(new RefreshEntity(true), AppConstants.REFRESH_TAG_TO);
            }
        });
    }
    @Subscriber(tag = AppConstants.REFRESH_TAG_RETURN)
    public void refreshReturn(RefreshEntity entity){
        refreshImage.setImageResource(R.mipmap.ic_refresh_normal);
    }
    @Subscriber(tag = AppConstants.URL_RETURN_DOWN)
    public void tagReturn(UrlEntity entity){
        tag = entity.getTag();
        days = entity.getDays();
        type = entity.getType();
        int count = StartUtil.getCount(getContext());
        urlEntity = new UrlEntity(tag,type,days,count);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        String className = name.getClassName();
        if (className.equals(DownloadService.class.getName())) {
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
