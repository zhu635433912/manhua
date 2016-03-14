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
}
