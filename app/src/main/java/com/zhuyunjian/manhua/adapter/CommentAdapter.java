package com.zhuyunjian.manhua.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhuyunjian.library.ListBaseAdapter;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.UserEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/3/15.
 */
public class CommentAdapter extends ListBaseAdapter <UserEntity>{

    public CommentAdapter(List<UserEntity> list, Context context) {
        super(list, context);

    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comment_list, parent, false);
            holder = new ViewHolder() ;
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.itemCommentUser = (SimpleDraweeView) convertView.findViewById(R.id.item_comment_user);
        holder.itemCommentUserName = (TextView) convertView.findViewById(R.id.item_comment_user_name);
        holder.itemCommentDate = (TextView) convertView.findViewById(R.id.item_comment_date);
        holder.itemCommentTitle = (TextView) convertView.findViewById(R.id.item_comment_title);
        holder.commentLikeNumber = (TextView) convertView.findViewById(R.id.comment_like_number);

        holder.itemCommentUser.setImageURI(Uri.parse(list.get(position).getUser_profile_image_url()));
        holder.itemCommentUserName.setText(list.get(position).getUser_name());
        holder.itemCommentDate.setText(new SimpleDateFormat("MM-dd HH:mm").format(list.get(position).getCreate_time()));
        holder.itemCommentTitle.setText(list.get(position).getText());
        holder.commentLikeNumber.setText(list.get(position).getDigg_count()+"");
        return convertView;
    }


    public static class ViewHolder {
//        @Bind(R.id.item_comment_user)
        private SimpleDraweeView itemCommentUser;
//        @Bind(R.id.item_comment_user_name)
        private TextView itemCommentUserName;
//        @Bind(R.id.item_comment_date)
        private TextView itemCommentDate;
//        @Bind(R.id.item_comment_title)
        private  TextView itemCommentTitle;
//        @Bind(R.id.comment_like_number)
        private  TextView commentLikeNumber;


    }
}
