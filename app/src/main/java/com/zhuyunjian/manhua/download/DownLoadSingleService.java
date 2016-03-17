package com.zhuyunjian.manhua.download;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IntentService 内部包含一个工作线程，每一次 startService 都会将
 * onStartCommand 中的 intent 传递；在子线程执行；
 */
public class DownLoadSingleService extends IntentService {
    private static final String TAG = "DownLoadSingleService";

    // 构造方法，必须要调用父类的 super(name) 这个构造；
    // 因为清单文件注册Service一定要有无参构造；
    public DownLoadSingleService() {
        //参数:为工作线程起名字
        super("OnlyForDebug");
    }

    /**
     * onHandleIntent 实际上就是处理 startService 传递的Intent参数的方法；
     * 这个方法运行在子线程；
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        Log.d(TAG, "开始下载 " + url);
        //SystemClock.sleep(1000);
        String data = httpGet(url);
        Log.d(TAG, "下载完成 " + data);
    }

    /**
     * 网络下载数据(GET请求)
     *
     * @param url
     * @return
     */
    public String httpGet(String url) {
        StringBuilder builder = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            URL webSite = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) webSite.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = "";
                builder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
