package com.p2p.dsad.ganhuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.RestVideo;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.ResultsBean;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoActivity extends BaseActivity
{

    @Bind(R.id.web_video)
    WebView webVideo;
    private ResultsBean video;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
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
        confingwebview();
    }
    private void confingwebview()
    {
        webVideo.getSettings().setJavaScriptEnabled(true);
        webVideo.loadUrl(video.getUrl());
        webVideo.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                webVideo.loadUrl(url);
                return true;
            }
        });

    }
    private void inintsession()
    {

    }
    public void getData()
    {
        Intent intent = getIntent();
        video = (ResultsBean) intent.getSerializableExtra("video");
    }
}
