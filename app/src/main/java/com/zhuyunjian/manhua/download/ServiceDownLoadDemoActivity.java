package com.zhuyunjian.manhua.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhuyunjian.manhua.R;


/**
 * 混合服务
 * 实现后台多任务下载
 */
public class ServiceDownLoadDemoActivity extends AppCompatActivity implements ServiceConnection {
    private static final String URL1 = "http://apk.hiapk.com/appdown/com.sgame.ad2048";
    private static final String URL2 = "http://apk.hiapk.com/appdown/com.nerser.ccser";
    private TextView tvTask1;
    private TextView tvTask2;
    private ProgressBar pbTask1;
    private ProgressBar pbTask2;
    public static final int TASK1_ID = 1;
    public static final int TASK2_ID = 2;
    public static Handler HANDLER_DOWN;
    private DownLoadMultipleService.DownController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_down_load_demo);

        tvTask1 = (TextView) findViewById(R.id.tv_task1);
        tvTask2 = (TextView) findViewById(R.id.tv_task2);
        pbTask1 = (ProgressBar) findViewById(R.id.pb_task1);
        pbTask2 = (ProgressBar) findViewById(R.id.pb_task2);

        init();

        HANDLER_DOWN = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                switch (msg.what) {
                    case TASK1_ID:
                        pbTask1.setMax(msg.arg1);
                        pbTask1.setProgress(msg.arg2);
                        break;
                    case TASK2_ID:
                        pbTask2.setMax(msg.arg1);
                        pbTask2.setProgress(msg.arg2);
                        break;
                }
            }
        };
        //绑定服务
        Intent intent = new Intent(this, DownLoadMultipleService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    private void init() {
        tvTask1.setText(URL1);
        tvTask2.setText(URL2);
        if(controller != null){
            pbTask1.setMax(controller.getTotalLengthByTaskId(TASK1_ID));
            pbTask1.setProgress(controller.getCurrentLengthByTaskId(TASK1_ID));
            pbTask2.setMax(controller.getTotalLengthByTaskId(TASK2_ID));
            pbTask2.setProgress(controller.getTotalLengthByTaskId(TASK2_ID));
        }
    }


    public void btnDownload(View view) {
        Intent intent = new Intent(this, DownLoadMultipleService.class);
        intent.putExtra("taskId1", TASK1_ID);
        intent.putExtra("task1", URL1);
        intent.putExtra("taskId2", TASK2_ID);
        intent.putExtra("task2", URL2);
        //启动服务
        startService(intent);
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        String className = name.getClassName();
        if (className.equals(ServiceDownLoadDemoActivity.class.getName())) {
            controller = (DownLoadMultipleService.DownController) service;
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        controller = null;
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }
}
