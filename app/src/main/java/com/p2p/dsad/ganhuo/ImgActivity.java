package com.p2p.dsad.ganhuo;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.ResultsBean;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.WomanBean;
import com.p2p.dsad.ganhuo.utlis.ConnectionUtils;
import com.p2p.dsad.ganhuo.utlis.PictUtil;
import com.p2p.dsad.ganhuo.utlis.TosatUtils;
import com.p2p.dsad.ganhuo.utlis.UiUtlis;
import com.trycatch.mysnackbar.TSnackbar;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ImgActivity extends BaseActivity {

    @Bind(R.id.img_img_menv)
    ImageView imgImgMenv;
    private ResultsBean woman_bean;
    private Bitmap background_bitmap;
    private WallpaperManager wall_manager;
    private TSnackbar top_snackbar;
    private Snackbar bottom_snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    private void inintdata()
    {
        //获取数据
        getData();
    }
    private void inintui()
    {
        configimg();
        inintBottomAndTop();
    }

    private void inintBottomAndTop()
    {
        top_snackbar =  TSnackbar.make(imgImgMenv,"设为当前壁纸",TSnackbar.LENGTH_LONG);
        top_snackbar.setBackgroundColor(UiUtlis.getColor(R.color.traslate));
        top_snackbar.setDuration(2000);
        bottom_snackbar = Snackbar.make(imgImgMenv,"提供者:"+woman_bean.getWho(),Snackbar.LENGTH_LONG);
        View v = bottom_snackbar.getView();
        v.setBackgroundColor(UiUtlis.getColor(R.color.traslate));
        bottom_snackbar.setDuration(2000);
        bottom_snackbar.setActionTextColor(Color.WHITE);
    }

    private void configimg()
    {
        //加载图片
        Glide.with(getApplicationContext()).load(woman_bean.getUrl()).into(imgImgMenv);
        //加载一份bitmap给这个管理器
        wall_manager = WallpaperManager.getInstance(getApplicationContext());
        //加载背景资源
        ConnectionUtils.getData(woman_bean.getUrl(), new BitmapCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }
            @Override
            public void onResponse(Bitmap response, int id)
            {
                background_bitmap = response;
            }
        });
    }

    private void inintsession()
    {
        //设置点击事件
        imgImgMenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                configTobSnackBar();
                configBottomBar();
            }
        });
    }

    private void configBottomBar()
    {

        bottom_snackbar.setAction("保存至本地", new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        try
                        {
                            PictUtil.saveToFile(new File(PictUtil.getSavePath(),woman_bean.getGanhuo_id()+".jpeg"),background_bitmap);
                            TosatUtils.ShowToast("保存成功,保存至:"+PictUtil.getSavePath());
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                            TosatUtils.ShowToast("出错了:"+e.getMessage());
                        }
                    }
                });
        if (bottom_snackbar.isShown())
        {
            bottom_snackbar.dismiss();
        }
        else
        {
            bottom_snackbar.show();
        }
    }
    private void configTobSnackBar()
    {
        top_snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    if (background_bitmap!=null)
                    {
                        wall_manager.setBitmap(background_bitmap);
                        TosatUtils.ShowToast("设置成功");
                    }
                    else
                    {
                        TosatUtils.ShowToast("加载中");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if (top_snackbar.isShown())
        {
            top_snackbar.dismiss();
        }
        else
        {
            top_snackbar.show();
        }
    }

    public void getData()
    {
        Intent intent = getIntent();
        woman_bean = (ResultsBean) intent.getSerializableExtra("img");
    }
}
