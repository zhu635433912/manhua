package com.zhuyunjian.manhua.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhuyunjian.library.ListBaseAdapter;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.utils.SpinnerData;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/3/14.
 */
public class HeadAdapter extends ListBaseAdapter {
    private List<Map<String,Object>> list ;
    public HeadAdapter(List<Map<String,Object>> list, Context context) {
        super(list, context);
        this.list = list;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_head_list,parent,false);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.imageView = (ImageView) convertView.findViewById(R.id.head_list_image);
        holder.imageView.setImageResource((Integer) list.get(position).get(SpinnerData.LOG));
        return convertView;
    }

    public static class ViewHolder{
        private ImageView imageView;
    }
}
