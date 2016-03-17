package com.zhuyunjian.library;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 判断是否第一次启动app
 * Created by dell on 2016/3/4.
 */
public class StartUtil {
    public static final String APP_START_KEY = "isFirst";
    public static final String APP_START_FIRST = "manhua_first";
    public static final String APP_THEME_LIGHT = "isLight";
    public static final String APP_LIGHT_KEY = "light_model";
    public static final String DOWN_COUNT = "down_count";
    public static final String DOWN_COUNT_KEY = "down_count_key";
    public static boolean isFirst(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_START_FIRST,Context.MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean(APP_START_KEY,true);
        if (isFirst){
            preferences.edit().putBoolean(APP_START_KEY,false).commit();
        }
        return isFirst;
    }
//public static boolean isFirst(Context context){
//        SharedPreferences preferences = context.getSharedPreferences("first",Context.MODE_PRIVATE);
//        boolean isFirst = preferences.getBoolean("isFirst",true);
//        if (isFirst){
//            preferences.edit().putBoolean("isFirst",false);
//        }
//        return isFirst;
//}
    public static boolean isLight(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_THEME_LIGHT,Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(APP_LIGHT_KEY,true);
        return isLight;
    }

    public static void editLight(Context context,boolean isNight){
        SharedPreferences preferences = context.getSharedPreferences(APP_THEME_LIGHT,Context.MODE_PRIVATE);
        preferences.edit().putBoolean(APP_LIGHT_KEY,isNight).commit();
    }

    public static int getCount(Context context){
        SharedPreferences preferences = context.getSharedPreferences(DOWN_COUNT,Context.MODE_PRIVATE);
        int count = preferences.getInt(DOWN_COUNT_KEY,20);
        return count;
    }

    public static void putCount(Context context,int count){
        SharedPreferences preferences = context.getSharedPreferences(DOWN_COUNT,Context.MODE_PRIVATE);
        preferences.edit().putInt(DOWN_COUNT_KEY,count).commit();
    }
}
