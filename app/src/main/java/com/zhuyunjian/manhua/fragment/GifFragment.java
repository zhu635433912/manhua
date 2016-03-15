package com.zhuyunjian.manhua.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.adapter.GifAdapter;
import com.zhuyunjian.manhua.adapter.RecentAdapter;
import com.zhuyunjian.manhua.entity.DataEntity;
import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.presenter.HeavyPresenter;
import com.zhuyunjian.manhua.presenter.impl.HeavyPresenterImpl;
import com.zhuyunjian.manhua.ui.CommentActivity_;
import com.zhuyunjian.manhua.ui.GifCommentActivity_;
import com.zhuyunjian.manhua.utils.SpinnerData;
import com.zhuyunjian.manhua.view.RecentView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_gif)
public class GifFragment extends BaseFragment implements RecentView,PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    @ViewById(R.id.frag_gif_list)
    PullToRefreshListView listView;

    private String tag,days,type;
    private int count = 20;

    private ArrayList<DataEntity> list = new ArrayList<>();
    private GifAdapter adapter;
    private HeavyPresenter presenter;
    @Override
    public void before() {
        tag = getArguments().getString(SpinnerData.TAG);
        days = getArguments().getString(SpinnerData.DAYS);
        type = getArguments().getString(SpinnerData.TYPE);

        presenter = new HeavyPresenterImpl(this,type,tag,days,count);

    }

    @Override
    public void intiView() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        adapter = new GifAdapter(list,getContext());
        listView.setAdapter(adapter);
        presenter.getHeavy();
    }

    @Override
    public void success(HeavyEntity entity) {
        listView.onRefreshComplete();
        if (count == 20) {
            list.clear();
            list.addAll(entity.getData());
        }
        if (list.size()< entity.getData().size()){
            for (int i = list.size(); i < entity.getData().size(); i++) {
                list.add(entity.getData().get(i));
            }
        }
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void failed() {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        count = 20;
        new HeavyPresenterImpl(this,type,tag,days,count).getHeavy();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        count = count + 20;
        new HeavyPresenterImpl(this,type,tag,days,count).getHeavy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String sort = days.equals("0")?"recent":"day";
//        Toast.makeText(getContext(),dataEntity.getDescription()+"", Toast.LENGTH_SHORT).show();
        startActivity(GifCommentActivity_.intent(getContext())
                .extra(AppConstants.IMAGE_URL,list.get(position-1).getLarge_url_list().get(0).getUrl())
                .extra(AppConstants.TITLE,list.get(position-1).getDescription())
                .extra(AppConstants.DING_COUNT,list.get(position-1).getDigg_count()+"")
                .extra(AppConstants.CAI_COUNT,list.get(position-1).getBury_count()+"")
                .extra(AppConstants.COLLECT_COUNT,list.get(position-1).getRepin_count()+"")
                .extra(AppConstants.GROUP_ID,list.get(position-1).getGroup_id()+"")
                .extra(AppConstants.TAG_HEAVY,days)
                .extra(AppConstants.SORT,sort).get());
    }
}
