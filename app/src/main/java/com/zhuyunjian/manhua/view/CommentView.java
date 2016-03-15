package com.zhuyunjian.manhua.view;

import com.zhuyunjian.manhua.entity.CommentEntity;

/**
 * Created by dell on 2016/3/15.
 */
public interface CommentView {
    void success(CommentEntity entity);
    void failed();
}
