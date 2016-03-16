package com.zhuyunjian.manhua.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuyunjian.library.ListBaseAdapter;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.DataEntity;

import java.util.List;

/**
 * Created by dell on 2016/3/15.
 */
public class GifAdapter extends ListBaseAdapter {
    private List<DataEntity> list;
    private boolean isLike;
    public GifAdapter(List<DataEntity> list, Context context) {
        super(list, context);
        this.list = list;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_gif_list,parent,false);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.gif_item_sdView);
        holder.titleText = (TextView) convertView.findViewById(R.id.gif_item_title);
        holder.gifImage = (ImageView) convertView.findViewById(R.id.gif_like_pic);
//        holder.gifImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isLike){
//                    isLike = false;
//                    holder.gifImage.setImageResource(R.mipmap.ic_action_favor_on_normal);
//                }
//            }
//        });
        holder.numberText = (TextView) convertView.findViewById(R.id.gif_item_like_number);
        holder.simpleDraweeView.setImageURI(Uri.parse(list.get(position).getMiddle_url_list().get(0).getUrl()));
        holder.titleText.setText(list.get(position).getDescription());
        holder.numberText.setText(list.get(position).getRepin_count()+"");
        return convertView;
    }

    private class ViewHolder{
        private SimpleDraweeView simpleDraweeView;
        private TextView titleText,numberText;
        private ImageView gifImage;
    }
}
