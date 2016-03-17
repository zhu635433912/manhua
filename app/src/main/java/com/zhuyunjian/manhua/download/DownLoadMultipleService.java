package com.zhuyunjian.manhua.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多任务下载(使用AsyncTask进行并发处理)
 */
public class DownLoadMultipleService extends Service {
    private ExecutorService executor;
    //全局的map,key记录任务taskId,value记录总的进度和当前进度值
    private Map<Integer,List<Object>> map;

    public DownLoadMultipleService() {
    }

    @Override
    public void onCreate() {
        executor = Executors.newFixedThreadPool(5);
        map = new HashMap<Integer,List<Object>>();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //接收传递的url
        int taskId1 = intent.getIntExtra("taskId1", -1);
        String url1 = intent.getStringExtra("task1");
        int taskId2 = intent.getIntExtra("taskId2", -1);
        String url2 = intent.getStringExtra("task2");
        executor.execute(new MyThread(taskId1, url1));
        executor.execute(new MyThread(taskId2, url2));
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 自定义线程网络下载
     */
    public class MyThread implements Runnable {
        //任务编号
        private int taskId;
        //下载url
        private String url;

        public MyThread(int taskId, String url) {
            this.taskId = taskId;
            this.url = url;
        }

        //执行网络下载任务
        @Override
        public void run() {
            getDownload(taskId, url);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DownController();
    }

    public class DownController extends Binder{
        /**
         * 根据任务编号获取总进度
         */
        public int getTotalLengthByTaskId(int taskId){
            if(map.containsKey(taskId)){
                List<Object> list = map.get(taskId);
                if(list != null){
                    return (int)list.get(0);
                }
            }
            return 0 ;
        }
        /**
         * 根据任务编号获取总当前进度
         */
        public int getCurrentLengthByTaskId(int taskId){
            if(map.containsKey(taskId)){
                List<Object> list = map.get(taskId);
                if(list != null){
                    return (int)list.get(1);
                }
            }
            return 0 ;
        }
    }

    @Override
    public void onDestroy() {
        executor.shutdown();
    }


    /**
     * @param taskId
     * @param url
     */
    public void getDownload(int taskId, String url) {
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
                int totalLength = conn.getContentLength();
                byte[] buffer = new byte[1024 * 8];
                int len;
                int currentLength = 0;
                baos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    currentLength += len;
                    baos.write(buffer, 0, len);
                    SystemClock.sleep(300);
                    Message msg = Message.obtain(ServiceDownLoadDemoActivity.HANDLER_DOWN,taskId);
                    msg.what = taskId;
                    msg.arg1 = totalLength;
                    msg.arg2 = currentLength;
                    msg.sendToTarget();
                    //将信息记录到全局的Map集合中
                    List<Object> list = new ArrayList<Object>();
                    list.add(totalLength);
                    list.add(currentLength);
                    map.put(taskId,list);
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
