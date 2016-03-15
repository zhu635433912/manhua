package com.zhuyunjian.manhua.presenter.impl;

import com.zhuyunjian.manhua.entity.CommentEntity;
import com.zhuyunjian.manhua.model.ModelFactory;
import com.zhuyunjian.manhua.presenter.CommentPresenter;
import com.zhuyunjian.manhua.view.CommentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell on 2016/3/15.
 */
public class CommentPresenterImpl implements CommentPresenter {
    private CommentView commentView;
    private String group_id;
    private String sort;
    private int count;
    private int offset;

    public CommentPresenterImpl(CommentView commentView, String group_id, String sort, int count, int offset) {
        this.commentView = commentView;
        this.group_id = group_id;
        this.sort = sort;
        this.count = count;
        this.offset = offset;
    }

    @Override
    public void getComment() {
        ModelFactory.getInstance().getCommentModel().getCommentData(new Callback<CommentEntity>() {
            @Override
            public void onResponse(Call<CommentEntity> call, Response<CommentEntity> response) {
                commentView.success(response.body());
            }

            @Override
            public void onFailure(Call<CommentEntity> call, Throwable t) {
                commentView.failed();
            }
        },group_id,sort,count,offset);
    }
}
