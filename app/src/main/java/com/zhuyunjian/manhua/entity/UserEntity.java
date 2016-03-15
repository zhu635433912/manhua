package com.zhuyunjian.manhua.entity;

/**
 * Created by dell on 2016/3/15.
 */
public class UserEntity {
        private String text;
        private int create_time;
        private long id;
        private int user_bury;
        private long user_id;
        private int bury_count;
        private int digg_count;
        private String user_name;
        private String user_profile_image_url;

        public void setText(String text) {
            this.text = text;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setUser_bury(int user_bury) {
            this.user_bury = user_bury;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setUser_profile_image_url(String user_profile_image_url) {
            this.user_profile_image_url = user_profile_image_url;
        }

        public String getText() {
            return text;
        }

        public int getCreate_time() {
            return create_time;
        }

        public long getId() {
            return id;
        }

        public int getUser_bury() {
            return user_bury;
        }

        public long getUser_id() {
            return user_id;
        }

        public int getBury_count() {
            return bury_count;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getUser_profile_image_url() {
            return user_profile_image_url;
        }

}
