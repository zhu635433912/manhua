package com.zhuyunjian.manhua.utils;

import android.text.TextUtils;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.App_;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/3/14.
 */
public class ParamsMap extends HashMap<String, String>{
    public ParamsMap() {
//        put(UrlUtils.IID,"3868215609");
        put(UrlUtils.DEVICE_ID,DeviceUtil.getDeviceId(App_.getApp()));
        put(UrlUtils.AC,"wifi");
        put(UrlUtils.CHANNEL,"baidu");
        put(UrlUtils.AID,"1");
        put(UrlUtils.APP_NAME,DeviceUtil.getAppName(App_.getApp(),DeviceUtil.getAppProcessId()));
        put(UrlUtils.VERSION_CODE,"242");
        put(UrlUtils.DEVICE_PLATFORM,"android");
        put(UrlUtils.DEVICE_TYPE,DeviceUtil.getPhoneModel());
        put(UrlUtils.OS_API,DeviceUtil.getBuildLevel()+"");
        put(UrlUtils.OS_VERSION,DeviceUtil.getBuildVersion());
        put(UrlUtils.OPENUDID,"b7a29d4e8d8caaef");
    }

    public void put(String key, int value) {
        super.put(key, value + "");
    }

    @Override
    public String put(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value))
            return "";
        return super.put(key, value);
    }
}
