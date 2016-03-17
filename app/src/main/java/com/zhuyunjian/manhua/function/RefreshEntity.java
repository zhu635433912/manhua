package com.zhuyunjian.manhua.function;

/**
 * Created by dell on 2016/3/17.
 */
public class RefreshEntity {

    private boolean isRefresh;

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public RefreshEntity(boolean isRefresh) {

        this.isRefresh = isRefresh;
    }
}
