package com.p2p.dsad.ganhuo.api;

/**
 * 干货集中营的api
 * Created by dsad on 2017/9/13.
 */

public class GanApi
{
    //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    //默认条数(包括搜索,包括其他列表展示条数)
    public static final String SP_GAN_NAME="ganio";
    public static final String DEFAUT_LISTSIZE = "20";
    public static final String DEFAUT_PAGE="1";
    //参数
    public static final String COUNT="count";
    public static final String PAGE = "page";
    //基类地址开始
    //总基地
    public static final String BASEURL="http://gank.io/api/";
    //分类取
    //示例:http://gank.io/api/search/query/listview/category/android/count/20/page/1
    public static final String SEARCH =BASEURL+"search/query/listview/category/";
    //随机推荐
    //示例:history/content/2/1
    //2条数据,取第一页
    public static final String COMMEN =BASEURL+"history/content/";
    //分类页面
    public static final String ALL = SEARCH+"all/";
    public static final String ANDROID = SEARCH+"Android/";
    public static final String IOS=SEARCH+"iOS/";
    public static final String REST_VIDEO=SEARCH+"休息视频/";
    public static final String FULI = SEARCH+"福利/";
    public static final String TUOZAN = SEARCH+"拓展资源/";
    public static final String WEB=SEARCH+"前端/";
    public static final String COMMENT = SEARCH+"瞎推荐/";
    public static final String APP = SEARCH+"App/";


}
