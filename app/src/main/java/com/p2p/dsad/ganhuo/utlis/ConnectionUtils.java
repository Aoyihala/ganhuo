package com.p2p.dsad.ganhuo.utlis;

import com.p2p.dsad.ganhuo.api.GanApi;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 网络链接工具
 * Created by dsad on 2017/8/29.
 */

public class ConnectionUtils
{
    private static Request request;
    private ConnectionUtils()
    {

    }
    static
    {
        //已在appliction里做配置


    }

    /**
     * 拼接url
     * @param baseurl 基本的url
     * @param name 参数名
     * @param url 参数值
     * @return
     */
    public static String getNormalUrls(String baseurl,String name,String url)
    {
        return baseurl+"?"+name+"="+url;

    }

    /***
     *
     * 多个参数的拼接
     * @param baseurl
     * @param urlparams
     * @return
     */

    public static String getUrls(String baseurl, Map<String,String> urlparams)
    {
        StringBuffer result = new StringBuffer();
        result.append(baseurl).append("?");
        Set<String> keys = urlparams.keySet();
        for (String key : keys)
        {
            result.append(key).append("=").append(urlparams.get(key)).append("&");
        }
        return result.toString().substring(0, result.toString().length()-1);
    }

    /**
     * 联网获取数据的方法
     * @param url 请求体
     * @param back 回调方法(需在主线程实现)
     */
    public static void getData(String url,StringCallback back)
    {
        OkHttpUtils.get().url(url).build().execute(back);
    }

    /**
     * 联网加载图片
     * @param url url
     * @param bitmapCallback 回调
     */
    public static void getData(String url, BitmapCallback bitmapCallback)
    {
        OkHttpUtils.get().url(url).build().execute(bitmapCallback);
    }

    /**
     * 只做联网操作,回调方法留空
     * @param url 请求实体
     */
    public static void getData(String url)
    {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }

    /***
     * 首页请求/参数的设置
     * @param BaseUrl
     * @param value1 值1
     * @param value2 值2
     * @return
     */
    public static String getCategoryUrls(String BaseUrl,String value1,String value2)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaseUrl).append(GanApi.COUNT+"/").append(value1+"/").append(GanApi.PAGE+"/").append(value2);
        return buffer.toString();
    }

    /**
     * 历史推荐
     * @param BaseUrl
     * @param value1
     * @param value2
     * @return
     */
    public static String getHistroyUrls(String BaseUrl,String value1,String value2)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaseUrl).append(value1+"/").append(value2);
        return buffer.toString();
    }

}
