package com.p2p.dsad.ganhuo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.p2p.dsad.ganhuo.api.MeiTuApi;
import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.bean.PicBean;
import com.p2p.dsad.ganhuo.fragment.android.AndroidFragment;
import com.p2p.dsad.ganhuo.fragment.app.AppFragment;
import com.p2p.dsad.ganhuo.fragment.commen.CommenFragment;
import com.p2p.dsad.ganhuo.fragment.fuli.FuliFragment;
import com.p2p.dsad.ganhuo.fragment.ios.IosFragment;
import com.p2p.dsad.ganhuo.fragment.restvideo.RestVideoFragment;
import com.p2p.dsad.ganhuo.fragment.tuozan.TuoZanFragment;
import com.p2p.dsad.ganhuo.fragment.web.WebFragment;
import com.p2p.dsad.ganhuo.utlis.TosatUtils;
import com.p2p.dsad.ganhuo.utlis.UiUtlis;
import com.p2p.dsad.ganhuo.utlis.gilde.ImageCatchUtil;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.ActionView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.btn_home_menu)
    ActionView btnHomeMenu;
    @Bind(R.id.top_home_bar)
    Toolbar topHomeBar;
    @Bind(R.id.tab_home_layout)
    TabLayout tabHomeLayout;
    List<String> tab_tltles = new ArrayList<>();
    List<Fragment> all_fragment = new ArrayList<>();
    @Bind(R.id.viewpage_home)
    ViewPager viewpageHome;
    @Bind(R.id.nav_home_menu)
    NavigationView navHomeMenu;
    private static MyViewPagerAdpter my_adpter;
    @Bind(R.id.dra_layout)
    DrawerLayout draLayout;
    private ImageView left_meun_bg;
    private TextView tv_leftimgtitle;
    private PicBean pic_bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    //看这里
    //初始化数据
    private void inintdata() {
        //清理缓存
        cleargildecache();
        //权限申请
        getpermisson();
        //设置侧滑菜单
        configleftmenu();
        //获取标题
        getTabTitles();
        //初始化fragment
        inintfragments();


    }

    private void cleargildecache()
    {
        ImageCatchUtil.getInstance().clearImageDiskCache();
    }

    private void configleftmenu()
    {

        navHomeMenu.setItemIconTintList(null);
        left_meun_bg = navHomeMenu.getHeaderView(0).findViewById(R.id.img_leftmenu_bg);
        tv_leftimgtitle = navHomeMenu.getHeaderView(0).findViewById(R.id.tv_leftmenu_title);
        /**
         *原地址作废
        ConnectionUtils.getData(MeiTuApi.MEITU, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                Log.e("dd",e.getMessage());
            }
            @Override
            public void onResponse(String response, int id) {
                pic_bean = JSON.parseObject(response,PicBean.class);
                Glide.with(getApplicationContext()).load(pic_bean.getLink()).into(left_meun_bg);
                tv_leftimgtitle.setText("\t\t"+pic_bean.getDescribe());
                tv_leftimgtitle.setVisibility(View.VISIBLE);
            }
        });
         **/
        Glide.with(getApplicationContext()).load(MeiTuApi.BINGYING).into(left_meun_bg);
        tv_leftimgtitle.setVisibility(View.VISIBLE);
        tv_leftimgtitle.setText("Github/@Aoyihala");
    }

    //初始化ui
    private void inintui() {
        //tablayou的设定
        configtablayout();
        //viewpager的设定
        configviewpager();
    }

    private void inintsession()
    {
        //打开菜单
        btnHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (draLayout.isDrawerOpen(Gravity.LEFT))
                {
                    draLayout.closeDrawers();
                }
                else
                {
                    draLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        //设置菜单点击事件
        navHomeMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.action_about:
                        TosatUtils.ShowToast("请点击上面的github");
                        break;
                    case R.id.action_github:
                        Uri myBlogUri = Uri.parse("https://github.com/Aoyihala/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, myBlogUri);
                        startActivity(intent);
                        break;
                    case R.id.action_setting:
                        startAcctivity(SettingActivity.class,false);
                        break;
                    case R.id.action_myfavorite:
                        //收藏
                        startAcctivity(MyFavoriteActivity.class,false);
                        break;
                    case R.id.action_shareapp:
                        //分享
                        showShare();
                        break;
                }
                return true;
            }
        });
    }

    public void getTabTitles()
    {
        tab_tltles = UiUtlis.getViewPagerTitle();
    }

    private void inintfragments()
    {
        //初始化fragment
        all_fragment.add(new AndroidFragment());
        all_fragment.add(new IosFragment());
        all_fragment.add(new RestVideoFragment());
        all_fragment.add(new FuliFragment());
        all_fragment.add(new TuoZanFragment());
        all_fragment.add(new WebFragment());
        all_fragment.add(new CommenFragment());
        all_fragment.add(new AppFragment());
    }

    private void configviewpager() {
        my_adpter = new MyViewPagerAdpter(getSupportFragmentManager(), all_fragment, tab_tltles);
        viewpageHome.setAdapter(my_adpter);
        viewpageHome.setOffscreenPageLimit(my_adpter.getCount());

    }

    private void configtablayout() {
        //可滚动
        tabHomeLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //与viewpager绑定
        tabHomeLayout.setupWithViewPager(viewpageHome);
    }

    public void getpermisson()
    {

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
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("干货集中营");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 内部类不能持有外部类引用
     * 使用静态内部类可以避免
     */
    static class MyViewPagerAdpter extends FragmentPagerAdapter
    {
        private List<String> titles;
        private List<Fragment> fragmets;
        public MyViewPagerAdpter(FragmentManager fm, List<Fragment> fragmets, List<String> titles) {
            super(fm);
            this.fragmets = fragmets;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position)
        {
            return fragmets.get(position);
        }

        @Override
        public int getCount() {
            return fragmets.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
