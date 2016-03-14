package com.zhuyunjian.manhua.utils;

import com.zhuyunjian.manhua.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/3/14.
 */
public class SpinnerData {
    public static final String TAG = "tag";
    public static final String TYPE = "type";
    public static final String DAYS = "days";

    public static final int[] heavys = new int[]{R.mipmap.title_recent_heavy,
            R.mipmap.title_hot_today,R.mipmap.title_hot_week};
    public static final int[] comics = new int[]{R.mipmap.title_recent_heavy,
            R.mipmap.title_hot_today,R.mipmap.title_hot_week};
    public static final int[] mengs = new int[]{R.mipmap.title_recent_heavy,
            R.mipmap.title_hot_today,R.mipmap.title_hot_week};
    public static final int[] gifs = new int[]{R.mipmap.title_recent_heavy,
            R.mipmap.title_hot_today,R.mipmap.title_hot_week};
    private List<Map<String, Object>> data;
    public SpinnerData() {
        data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put(TAG, R.mipmap.menu_hot_today_normal);
        map2.put(TYPE, "top");
        map2.put(DAYS, "1");
        data.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put(TAG, R.mipmap.menu_hot_week_normal);
        map3.put(TYPE, "top");
        map3.put(DAYS, "7");
        data.add(map3);
    }

    public  List<Map<String, Object>> getHeavyData() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TAG, R.mipmap.menu_recent_heavy_normal);
        map.put(TYPE, "recent");
        map.put(DAYS, "0");
        data.add(0,map);

        return data;
    }
    public  List<Map<String, Object>> getComicData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TAG, R.mipmap.menu_recent_heavy_normal);
        map.put(TYPE, "recent");
        map.put(DAYS, "0");
        data.add(0,map);

        return data;
    }
    public  List<Map<String, Object>> getMengData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TAG, R.mipmap.menu_recent_heavy_normal);
        map.put(TYPE, "recent");
        map.put(DAYS, "0");
        data.add(0,map);

        return data;
    }
    public  List<Map<String, Object>> getGifData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TAG, R.mipmap.menu_recent_heavy_normal);
        map.put(TYPE, "recent");
        map.put(DAYS, "0");
        data.add(0,map);

        return data;
    }
}
