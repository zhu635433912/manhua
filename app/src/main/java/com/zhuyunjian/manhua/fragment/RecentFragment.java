package com.zhuyunjian.manhua.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.adapter.RecentAdapter;
import com.zhuyunjian.manhua.api.OnRecyclerViewItemClickListener;
import com.zhuyunjian.manhua.entity.DataEntity;
import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.entity.UrlEntity;
import com.zhuyunjian.manhua.function.RefreshEntity;
import com.zhuyunjian.manhua.presenter.HeavyPresenter;
import com.zhuyunjian.manhua.presenter.impl.HeavyPresenterImpl;
import com.zhuyunjian.manhua.ui.CommentActivity_;
import com.zhuyunjian.manhua.utils.SpinnerData;
import com.zhuyunjian.manhua.view.RecentView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_recent)
public class RecentFragment extends BaseFragment implements RecentView, SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewItemClickListener {
    @ViewById(R.id.recent_list_swipe)
    SwipeRefreshLayout srfLayout;
    @ViewById(R.id.recent_recycler)
    RecyclerView recyclerView;
    private String tag,days,type;
    private int count = 20;

    private ArrayList<DataEntity> list = new ArrayList<>();
    private RecentAdapter adapter;
    private HeavyPresenter presenter;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
        tag = getArguments().getString(SpinnerData.TAG);
        days = getArguments().getString(SpinnerData.DAYS);
        type = getArguments().getString(SpinnerData.TYPE);

        presenter = new HeavyPresenterImpl(this,type,tag,days,count);

    }

    @Override
    public void intiView() {
        EventBus.getDefault().post(new UrlEntity(tag,type,days),AppConstants.URL_RETURN_DOWN);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new RecentAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        presenter.getHeavy();
        srfLayout.setRefreshing(true);
        srfLayout.setOnRefreshListener(this);
    }

    @Override
    public void success(HeavyEntity entity) {
        if (count == 20) {
            list.clear();
            list.addAll(entity.getData());
        }
        if (list.size()< entity.getData().size()){
            for (int i = list.size(); i < entity.getData().size(); i++) {
                list.add(entity.getData().get(i));
            }
        }
        EventBus.getDefault().post(new RefreshEntity(true),AppConstants.REFRESH_TAG_RETURN);
        srfLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View childAt = recyclerView.getChildAt(recyclerView.getChildCount()-1);
                int position  = recyclerView.getChildAdapterPosition(childAt);
                if (adapter.getItemCount() - position < 5){
                    count = count + 20;
                    new HeavyPresenterImpl(RecentFragment.this,type,tag,days,count).getHeavy();
                }
            }
        });
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void failed() {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        count = 20;
        new HeavyPresenterImpl(this,type,tag,days,count).getHeavy();
    }

    @Override
    public void onItemClick(View view, DataEntity dataEntity) {
        String sort = days.equals("0")?"recent":"day";
//        Toast.makeText(getContext(),dataEntity.getDescription()+"", Toast.LENGTH_SHORT).show();
        startActivity(CommentActivity_.intent(getContext())
                .extra(AppConstants.IMAGE_URL,dataEntity.getLarge_url_list().get(0).getUrl())
                .extra(AppConstants.TITLE,dataEntity.getDescription())
                .extra(AppConstants.DING_COUNT,dataEntity.getDigg_count()+"")
                .extra(AppConstants.CAI_COUNT,dataEntity.getBury_count()+"")
                .extra(AppConstants.COLLECT_COUNT,dataEntity.getRepin_count()+"")
                .extra(AppConstants.GROUP_ID,dataEntity.getGroup_id()+"")
                .extra(AppConstants.TAG_HEAVY,days)
                .extra(AppConstants.SORT,sort).get());
    }

    @Override
    public void initData() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_heavy,null);
        ImageView refreshImage = (ImageView) view.findViewById(R.id.head_refresh_image);
        refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ".....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscriber(tag = AppConstants.REFRESH_TAG_TO)
    public void refresh(RefreshEntity entity){
        new HeavyPresenterImpl(this,type,tag,days,20).getHeavy();
    }
}
