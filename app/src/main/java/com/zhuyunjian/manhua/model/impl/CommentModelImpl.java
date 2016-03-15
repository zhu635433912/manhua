package com.zhuyunjian.manhua.model.impl;

import com.zhuyunjian.manhua.App;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.entity.CommentEntity;
import com.zhuyunjian.manhua.model.CommentModel;
import com.zhuyunjian.manhua.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/3/15.
 */
public class CommentModelImpl extends CommentBaseModel implements CommentModel {

    public CommentModelImpl() {
    }

    @Override
    public void getCommentData(Callback<CommentEntity> callback,String group_id,String sort,int count,int offset) {
        ParamsMap map = new ParamsMap();
        map.put(AppConstants.GROUP_ID,group_id);
        map.put(AppConstants.COUNT,count);
        map.put(AppConstants.OFFSET,offset);
        map.put(AppConstants.SORT,sort);
        service.getComment(map).enqueue(callback);
    }
}
