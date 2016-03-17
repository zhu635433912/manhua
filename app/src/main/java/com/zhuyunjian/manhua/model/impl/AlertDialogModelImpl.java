package com.zhuyunjian.manhua.model.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.utils.DeviceUtil;

/**
 * Created by dell on 2016/3/17.
 */
public class AlertDialogModelImpl {

    public static AlertDialog setClearDialog(Context context, final TextView nowCacheText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示").setMessage("确定要清楚图片吗")
                .setCancelable(false)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog,int which){
                        DeviceUtil.deleteFolderFile(AppConstants.IMAGE_CACHE,true);
                        nowCacheText.setText( "当前缓存:"+ DeviceUtil.getFormatSize(DeviceUtil.getFolderSize(AppConstants.getCacheFile())));
                    }
                })
                .setNegativeButton("取消",null);

        AlertDialog clearDialog = builder.create();
        return clearDialog;
    }

    public static AlertDialog setChooseDialog(Context context,final TextView saveNumberText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1);
        adapter.add("前20张");adapter.add("前40张");adapter.add("前60张");adapter.add("前80张");adapter.add("前100张");
        builder.setTitle("选择下载数目")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = adapter.getItem(which);
                        saveNumberText.setText(item);
                    }
                }).setNegativeButton("取消",null);
        AlertDialog numberDialog = builder.create();
        return  numberDialog;
    }
}
