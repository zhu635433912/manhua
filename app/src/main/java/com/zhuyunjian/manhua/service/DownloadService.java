package com.zhuyunjian.manhua.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.download.ServiceDownLoadDemoActivity;
import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.entity.UrlEntity;
import com.zhuyunjian.manhua.presenter.impl.HeavyPresenterImpl;
import com.zhuyunjian.manhua.view.RecentView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell on 2016/3/17.
 */
public class DownloadService extends Service implements RecentView {
    private ExecutorService executor;
    private UrlEntity entity;
    public DownloadService() {
    }

    @Override
    public void onCreate() {
        executor = Executors.newFixedThreadPool(5);
        EventBus.getDefault().register(this);
        super.onCreate();
        new HeavyPresenterImpl(this,entity.getType(),entity.getTag(),entity.getDays(),entity.getCount()).getHeavy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new HeavyPresenterImpl(this,entity.getType(),entity.getTag(),entity.getDays(),entity.getCount()).getHeavy();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void success(HeavyEntity heavy) {
        for (int i = 0; i < heavy.getData().size(); i++) {
            executor.execute(new MyThread(heavy.getData().get(i).getLarge_url_list().get(0).getUrl()));
        }
    }

    @Override
    public void failed() {

    }

    /**
     * 自定义网络下载线程
     */
    public class MyThread implements Runnable{
        private String url;

        public MyThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            getDownload(url);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscriber(tag = AppConstants.URL_RETURN_TO)
    public void getData(UrlEntity urlEntity){
        entity = urlEntity;
    }
    /**
     * @param url
     */
    public void getDownload( String url) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL webSite = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) webSite.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                byte[] buffer = new byte[1024 * 8];
                int len;
                baos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
