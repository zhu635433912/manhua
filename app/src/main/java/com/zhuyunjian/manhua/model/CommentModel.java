package com.zhuyunjian.manhua.model;

import com.zhuyunjian.manhua.entity.CommentEntity;

import retrofit2.Callback;

/**
 * Created by dell on 2016/3/15.
 */
public interface CommentModel {
    void getCommentData(Callback<CommentEntity> callback,String group_id,String sort,int count,int offset);
}
