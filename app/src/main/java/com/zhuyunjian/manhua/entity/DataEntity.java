package com.zhuyunjian.manhua.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dell on 2016/3/14.
 */
public class DataEntity {
        private long tag_id;
        private String tag;
        private String description;
        private int bury_count;
        private int repin_count;
        private int digg_count;
        private int behot_time;
        private long group_id;
        @SerializedName("middle_url_list")
        private List<MiddleEntity> middle_url_list;

        public void setTag_id(long tag_id) {
            this.tag_id = tag_id;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public void setBehot_time(int behot_time) {
            this.behot_time = behot_time;
        }

        public void setGroup_id(long group_id) {
            this.group_id = group_id;
        }

        public void setMiddle_url_list(List<MiddleEntity> middle_url_list) {
            this.middle_url_list = middle_url_list;
        }

        public long getTag_id() {
            return tag_id;
        }

        public String getTag() {
            return tag;
        }

        public String getDescription() {
            return description;
        }

        public int getBury_count() {
            return bury_count;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public int getBehot_time() {
            return behot_time;
        }

        public long getGroup_id() {
            return group_id;
        }

        public List<MiddleEntity> getMiddle_url_list() {
            return middle_url_list;
        }

}
