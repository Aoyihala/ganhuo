package com.p2p.dsad.ganhuo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mukesh.MarkdownView;
import com.p2p.dsad.ganhuo.application.MyAppliction;
import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.bean.ResultsBean;
import com.p2p.dsad.ganhuo.db.GreenDaoUtil;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeans;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeansDao;
import com.p2p.dsad.ganhuo.utlis.SpUtils;

import at.markushi.ui.ActionView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class GoodActivity extends BaseActivity {

    @Bind(R.id.btn_good_back)
    ActionView btnGoodBack;
    @Bind(R.id.tv_good_title)
    TextView tvGoodTitle;
    @Bind(R.id.top_good_bar)
    Toolbar topGoodBar;
    @Bind(R.id.mark_good_view)
    MarkdownView markGoodView;
    @Bind(R.id.pro_good_loadview)
    ProgressBar proGoodLoadview;
    @Bind(R.id.img_good_favorite)
    ImageView imgGoodFavorite;
    private ResultsBean data;
    private SaveGoodsBeans goods_bean;
    private SaveGoodsBeansDao goods_bean_dao;
    public static final String GOODS_ACTIVTY = "good_favorite";
    public static final String GOODS_FAVORITR_KEY = "favorite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    private void inintdata() {
        //自动建好
        configdao();
        getData();
        ininttoolbar();
    }

    private void configdao()
    {
        goods_bean_dao = GreenDaoUtil.getdaosession().getSaveGoodsBeansDao();
    }

    private void ininttoolbar() {
        setSupportActionBar(topGoodBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载toolbar的工具栏
        getMenuInflater().inflate(R.menu.toolba_rmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_share:
                //分享
                showShare();
                break;
            case R.id.action_browse:
                //浏览器
                Uri myBlogUri = Uri.parse(data.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, myBlogUri);
                startActivity(intent);
                break;

        }

        return true;
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://github.com/Aoyihala/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(data.getDesc());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(data.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("干货集中营");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(data.getUrl());
        // 启动分享GUI
        oks.show(this);
    }

    private void deletefavorite()
    {
        //傻逼用户点了收藏又删掉
        goods_bean_dao.delete(goods_bean);
        Snackbar.make(markGoodView, "取消收藏", Snackbar.LENGTH_SHORT).show();

    }

    private void savefavorite() {
        goods_bean = new SaveGoodsBeans();
        goods_bean.setAuthor(data.getWho());
        goods_bean.setDesc(data.getDesc());
        goods_bean.setContent(data.getReadability());
        goods_bean.setTime(data.getPublishedAt());
        goods_bean.setGanhuo_id(data.getGanhuo_id());
        goods_bean.setUrl(data.getUrl());
        goods_bean.setSeclect(true);
        goods_bean.setType(data.getType());
        goods_bean_dao.insert(goods_bean);
        Snackbar.make(markGoodView, "收藏成功", Snackbar.LENGTH_SHORT).show();
    }

    private void inintui() {
        //配置markdown
        configmarkdown();
        //配置黑心
        configimgfav();
    }

    private void configimgfav()
    {
       goods_bean =  goods_bean_dao.queryBuilder().where(SaveGoodsBeansDao.Properties.Ganhuo_id.eq(data.getGanhuo_id()))
               .build().unique();
        if (goods_bean!=null)
        {
            imgGoodFavorite.setBackgroundResource(R.mipmap.ic_favorite_black);
        }
        else
        {
            imgGoodFavorite.setBackgroundResource(R.mipmap.ic_favorite_outline_black);
        }
    }

    private void configmarkdown() {
        markGoodView.setMarkDownText(data.getReadability());
    }

    public void inintsession() {
        //进度条
        markGoodView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    proGoodLoadview.setVisibility(View.GONE);
                    return;
                }
                proGoodLoadview.setVisibility(View.VISIBLE);
                proGoodLoadview.setProgress(newProgress);
            }
        });
        //返回按钮
        btnGoodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //收藏
        imgGoodFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //查询为空,给我收藏
               if (goods_bean==null)
               {
                   imgGoodFavorite.setBackgroundResource(R.mipmap.ic_favorite_black);
                   //收藏了,取消收藏
                    savefavorite();
               }
               else
               {
                   //如果是查到的话就取消收藏
                   imgGoodFavorite.setBackgroundResource(R.mipmap.ic_favorite_outline_black);
                    //取消收藏
                   deletefavorite();
                   //然后就不能收藏了,必须要重进,所以加上这句
                   goods_bean=null;
               }
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        data = (ResultsBean) intent.getSerializableExtra("result");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (markGoodView.canGoBack()) {
                markGoodView.goBack();
            } else {
                finish();
            }
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
