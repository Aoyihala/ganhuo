package com.p2p.dsad.ganhuo;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.db.GreenDaoUtil;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeans;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeansDao;
import com.p2p.dsad.ganhuo.utlis.imgloader.ImgCache;

import java.io.File;
import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.img_setting_icon)
    ImageView imgSettingIcon;
    @Bind(R.id.tv_setting_title)
    TextView tvSettingTitle;
    @Bind(R.id.tv_setting_cachesize)
    TextView tvSettingCachesize;
    @Bind(R.id.tv_setting_favoritesize)
    TextView tvSettingFavoritesize;
    @Bind(R.id.card_cache)
    CardView cardCache;
    @Bind(R.id.cache_favorite)
    CardView cacheFavorite;
    private List<SaveGoodsBeans> good_beans;
    private SaveGoodsBeansDao good_bean_dao;
    private android.os.Handler myhandler;
    private Message mymessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    private void inintdata() {
        //获取所有收藏
        getFavoriteItemsize();
    }

    private void inintui() {
        //缓存显示
        configtvcachesize();
        //收藏数量显示
        configfavoritesize();
        //收到通知(操作完成)
        recicved();
    }

    private void recicved()
    {
        myhandler = new android.os.Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 1:
                        //缓存清理完成
                        tvSettingCachesize.setText("缓存大小:0mb");
                        cancelProgressDialog();
                        break;
                    case 2:
                        //清理收藏完成
                        tvSettingFavoritesize.setText("收藏个数:0");
                        cancelProgressDialog();
                        break;
                }
            }
        };
    }

    private void configfavoritesize() {
        tvSettingFavoritesize.setText("收藏个数:" + good_beans.size());
    }

    private void configtvcachesize() {
        //先计算缓存
        //保留两位小数
        double result = getCacheSize()/ 1024 / 1024;
        String trueresult = String.format("%.2f", result);
        tvSettingCachesize.setText("缓存大小:" + trueresult + "mb");
    }

    /***
     * 获取缓存大小
     */
    public double getCacheSize() {
        //图片缓存
        double cache_size = 0;
        File imagefiles = new File(ImgCache.LOCAL_PATH);
        //递归获取所有文件大小
        if (imagefiles.exists()) {
            File[] imges = imagefiles.listFiles();

            for (File oneimage : imges) {
                cache_size += oneimage.length();
            }
        } else {
            cache_size = 0;
        }
        return cache_size;
    }

    public void getFavoriteItemsize() {
        good_bean_dao = GreenDaoUtil.getdaosession().getSaveGoodsBeansDao();
        good_beans = good_bean_dao.loadAll();
    }

    private void inintsession() {
        mymessage = myhandler.obtainMessage();
        //点击清理缓存
        cardCache.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //删除缓存
                deletcache();
            }
        });
        //点击清理收藏
        cacheFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletfavorite();
            }
        });
        myhandler.sendMessage(mymessage);
    }

    private void deletfavorite()
    {
        showProgressDialog("清理中");
        new Thread()
        {
            @Override
            public void run()
            {
                good_bean_dao.deleteAll();
                SystemClock.sleep(2000);
                myhandler.sendEmptyMessage(2);
            }
        }.start();

    }
    private void deletcache()
    {
        showProgressDialog("清理中");
        new Thread()
        {
            @Override
            public void run() {
                //图片缓存
                File imagefiles = new File(ImgCache.LOCAL_PATH);
                //这是直接把文件目录一起删除了,建议递归删除
                //imagefiles.delete();
                if (imagefiles.exists())
                {

                    File[] imges = imagefiles.listFiles();
                    for (File oneimage:imges)
                    {
                        oneimage.delete();
                    }
                    SystemClock.sleep(2000);
                    myhandler.sendEmptyMessage(1);
                }
            }
        }.start();


    }

}
