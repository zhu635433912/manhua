package com.zhuyunjian.manhua;

import java.io.File;

/**
 * Created by dell on 2016/3/14.
 */
public class AppConstants {
    public static final String FRAG_TYPE_ID = "type_id";

    public static final String TAG_HEAVY = "heavy";
    public static final String TAG_COMIC = "comic";
    public static final String TAG_MENG = "meng";
    public static final String TAG_GIF = "gif";
    public static final String TAG_MORE = "more";
    public static final String IMAGE_URL = "image_url";
    public static final String TITLE = "title";
    public static final String DING_COUNT = "ding_count";
    public static final String CAI_COUNT = "cai_count";
    public static final String COLLECT_COUNT = "collect_count";
    public static final String GROUP_ID = "group_id";

    public static final String COUNT = "count";
    public static final String OFFSET = "offset";
    public static final String SORT = "sort";

    public static final String REFRESH_TAG_RETURN = "refresh_tag_return";
    public static final String URL_RETURN_DOWN = "url_return_down";
    public static final String URL_RETURN_TO = "url_return_to";
    public static final String REFRESH_TAG_TO = " refresh_tag_to";
    public static final String USER_NAME = "user_name";
    public static final String IMAGE_CACHE = App_.getInstance().getCacheDir().getAbsolutePath()+"";
    public static File getCacheFile(){
        File file = new File(IMAGE_CACHE);
        return file;
    }

}
