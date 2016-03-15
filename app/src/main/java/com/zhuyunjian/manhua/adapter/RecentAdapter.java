package com.zhuyunjian.manhua.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.api.OnRecyclerViewItemClickListener;
import com.zhuyunjian.manhua.entity.DataEntity;

import java.util.List;

/**
 * Created by dell on 2016/3/14.
 */
public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> implements View.OnClickListener {
    private List<DataEntity> list;
    private Context context;
    private OnRecyclerViewItemClickListener listener;
    public RecentAdapter(List<DataEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_heavy_recycler,parent,false);
        view.setOnClickListener(this);
        return new RecentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getMiddle_url_list().get(0).getUrl()).into(holder.simpleDraweeView);
//        holder.simpleDraweeView.setImageURI(Uri.parse(list.get(position).getMiddle_url_list().get(0).getUrl()));
        holder.titleText.setText(list.get(position).getDescription());
        holder.itemView.setTag(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onItemClick(v, (DataEntity) v.getTag());
        }
    }



    public static class RecentViewHolder extends RecyclerView.ViewHolder{
        private ImageView simpleDraweeView;
        private TextView titleText,numberText;
        private ImageView likeImage,shareImage;
        private LinearLayout linearLayout;
        public RecentViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (ImageView) itemView.findViewById(R.id.item_recycler_sdview);
            titleText = (TextView) itemView.findViewById(R.id.item_recycler_title);
            numberText = (TextView) itemView.findViewById(R.id.item_bottom_number);
            likeImage = (ImageView) itemView.findViewById(R.id.item_like_pic);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_bottom_like);
            shareImage = (ImageView) itemView.findViewById(R.id.item_bottom_share);
        }
    }
}
