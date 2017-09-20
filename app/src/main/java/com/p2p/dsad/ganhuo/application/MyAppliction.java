package com.p2p.dsad.ganhuo.application;

import android.app.Application;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.mob.commons.SHARESDK;
import com.p2p.dsad.ganhuo.api.GanApi;
import com.p2p.dsad.ganhuo.api.MeiTuApi;
import com.p2p.dsad.ganhuo.bean.PicBean;
import com.p2p.dsad.ganhuo.utlis.ConnectionUtils;
import com.p2p.dsad.ganhuo.utlis.okhttp.NoVerifiedCheck;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.concurrent.TimeUnit;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 *
 * Created by dsad on 2017/9/13.
 */

public class MyAppliction extends Application
{
    private static Context  mContext;
    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
        inintsharesdk();
        inintokhttp();
        //基本权限
        hipermisson();

    }

    private void inintsharesdk()
    {

    }

    private void hipermisson()
    {

    }
    private void inintokhttp()
    {

        OkHttpClient c = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .hostnameVerifier(new NoVerifiedCheck())
                .build();
        OkHttpUtils.initClient(c);

    }
    public static Context getmContext() {
        return mContext;
    }


}
