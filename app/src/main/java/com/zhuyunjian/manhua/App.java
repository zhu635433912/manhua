package com.zhuyunjian.manhua;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.androidannotations.annotations.EApplication;

/**
 * Created by dell on 2016/3/14.
 */
@EApplication
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fresco.initialize(this);
        //默认图片的磁盘配置
//        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
//                .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())//缓存图片基路径
//                .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)//文件夹名
//                .setMaxCacheSize(FrescoConfig.MAX_DISK_CACHE_SIZE)//默认缓存的最大大小。
//                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)//缓存的最大大小,使用设备时低磁盘空间。
//                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE)//缓存的最大大小,当设备极低磁盘空间
//            .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
//            .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
//            .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
//            .setVersion(version)
//                .build();
    }

    public static App getInstance(){return app;}

    public static App getApp() {
        return app;
    }

    public static void setApp(App app) {
        App.app = app;
    }
}
