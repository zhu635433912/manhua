package com.zhuyunjian.manhua.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.adapter.CommentAdapter;
import com.zhuyunjian.manhua.entity.CommentEntity;
import com.zhuyunjian.manhua.entity.UserEntity;
import com.zhuyunjian.manhua.presenter.CommentPresenter;
import com.zhuyunjian.manhua.presenter.impl.CommentPresenterImpl;
import com.zhuyunjian.manhua.view.CommentView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_comment)
public class CommentActivity extends BaseActivity implements CommentView,PullToRefreshBase.OnRefreshListener2{
//    @ViewById(R.id.comment_gif_sdview)
    ImageView sdView;
//    @ViewById(R.id.comment_title)
    TextView titleText;
//    @ViewById(R.id.comment_ding_text)
    TextView dingText;
//    @ViewById(R.id.comment_cai_text)
    TextView caiText;
//    @ViewById(R.id.comment_collect_text)
    TextView collectText;
    @ViewById(R.id.head_offline_btn)
    ImageView returnImage;
    @ViewById(R.id.comments_ptr_listview)
    PullToRefreshListView ptrListView;
    @ViewById(R.id.head_mid_pic)
    ImageView midImage;
    private View headView;
    private CommentPresenterImpl presenter;
    private String imageUrl,title,ding,cai,collect,group_id,sort,days;
    private int count = 20;
    private int offset = -1 ;
    private CommentAdapter adapter;
    private List<UserEntity> list = new ArrayList<>();
    @Override
    public void before() {
        imageUrl = getIntent().getStringExtra(AppConstants.IMAGE_URL);
        title = getIntent().getStringExtra(AppConstants.TITLE);
        ding = getIntent().getStringExtra(AppConstants.DING_COUNT);
        cai = getIntent().getStringExtra(AppConstants.CAI_COUNT);
        collect = getIntent().getStringExtra(AppConstants.COLLECT_COUNT);
        group_id = getIntent().getStringExtra(AppConstants.GROUP_ID);
        sort = getIntent().getStringExtra(AppConstants.SORT);
        days = getIntent().getStringExtra(AppConstants.TAG_HEAVY);
        presenter = new CommentPresenterImpl(this,group_id,sort,count,offset+1);
    }

    @Override
    public void initView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headView = inflater.inflate(R.layout.comment_head,null);
        sdView = (ImageView) headView.findViewById(R.id.comment_gif_sdview);
        titleText = (TextView) headView.findViewById(R.id.comment_title);
        dingText = (TextView) headView.findViewById(R.id.comment_ding_text);
        caiText = (TextView) headView.findViewById(R.id.comment_cai_text);
        collectText = (TextView) headView.findViewById(R.id.comment_collect_text);
        changeTitle();
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void changeTitle() {
        if (days.equals("0"))
            midImage.setImageResource(R.mipmap.title_recent_heavy);
        else if (days.equals("1"))
            midImage.setImageResource(R.mipmap.title_hot_today);
        else
            midImage.setImageResource(R.mipmap.title_hot_week);
    }



    @Override
    public void initData() {
        ptrListView.setMode(PullToRefreshBase.Mode.BOTH);
        ptrListView.setOnRefreshListener(this);
        adapter = new CommentAdapter(list,this);
        ptrListView.getRefreshableView().addHeaderView(headView);
        ptrListView.setAdapter(adapter);
        Picasso.with(this).load(imageUrl).into(sdView);
        titleText.setText(title);
        dingText.setText("顶"+ding);
        caiText.setText("踩"+cai);
        collectText.setText(collect+"人收藏");


        presenter.getComment();
    }

    @Override
    public void success(CommentEntity entity) {
        if (offset == 0)
            list.clear();
        list.addAll(entity.getData());
        ptrListView.onRefreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failed() {
        Toast.makeText(CommentActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        offset = 0 ;
        new CommentPresenterImpl(this, group_id, sort, count, offset).getComment();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        offset += 20;
        new CommentPresenterImpl(this, group_id, sort, count, offset).getComment();
    }


}