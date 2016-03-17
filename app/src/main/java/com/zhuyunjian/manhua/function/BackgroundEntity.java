package com.zhuyunjian.manhua.function;

/**
 * Created by dell on 2016/3/17.
 */
public class BackgroundEntity {

    private boolean isNight;

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }

    public BackgroundEntity(boolean isNight) {

        this.isNight = isNight;
    }
}
