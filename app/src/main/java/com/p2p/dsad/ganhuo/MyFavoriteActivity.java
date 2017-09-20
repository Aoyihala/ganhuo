package com.p2p.dsad.ganhuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.p2p.dsad.ganhuo.adpter.MyFavoriteAdpter;
import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.bean.GoodsBean;
import com.p2p.dsad.ganhuo.bean.ResultsBean;
import com.p2p.dsad.ganhuo.db.GreenDaoUtil;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeans;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeansDao;
import com.p2p.dsad.ganhuo.utlis.SpaceRecyclerItem;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.ActionView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFavoriteActivity extends BaseActivity
{

    @Bind(R.id.btn_myfavorite_back)
    ActionView btnMyfavoriteBack;
    @Bind(R.id.recycler_myfavorite)
    RecyclerView recyclerMyfavorite;
    @Bind(R.id.top_myfavorite_bar)
    Toolbar topMyfavoriteBar;
    private MyFavoriteAdpter favorite_adpter;
    private List<SaveGoodsBeans> goods_beans;
    private SaveGoodsBeansDao goods_bean_dao;
    private ResultsBean bean_data;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        ininitsession();
    }
    private void inintdata()
    {

        //初始化recyclerview
        inintrecycler();
        //获取操作实体
        goods_bean_dao = GreenDaoUtil.getdaosession().getSaveGoodsBeansDao();
        //查询所有数据
        getAllFavorite();
    }
    public void getAllFavorite()
    {
        goods_beans = goods_bean_dao.loadAll();
        if (goods_beans!=null&&goods_beans.size()>0)
        {
            //设置适配器
            configrecycler();
        }
        else
        {
            //没有数据

        }
    }


    private void inintui()
    {

    }
    private void inintrecycler()
    {
        favorite_adpter = new MyFavoriteAdpter();
        favorite_adpter.setOnItemClickListener(new MyFavoriteAdpter.OnItmeClickListener() {
            @Override
            public void onItemClick(SaveGoodsBeans bean)
            {
                bean_data = new ResultsBean();
                bean_data.setDesc(bean.getDesc());
                bean_data.setGanhuo_id(bean.getGanhuo_id());
                bean_data.setPublishedAt(bean.getTime());
                bean_data.setReadability(bean.getContent());
                bean_data.setType(bean.getType());
                bean_data.setUrl(bean.getUrl());
                bean_data.setWho(bean.getAuthor());
                Intent intent = new Intent(MyFavoriteActivity.this,GoodActivity.class);
                intent.putExtra("result",bean_data);
                startActivity(intent);
            }
        });
        recyclerMyfavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //设置动画时长
        recyclerMyfavorite.addItemDecoration(new SpaceRecyclerItem(8));
        recyclerMyfavorite.getItemAnimator().setChangeDuration(1000);
        recyclerMyfavorite.getItemAnimator().setRemoveDuration(1000);
        //设置适配器
        recyclerMyfavorite.setAdapter(favorite_adpter);
    }
    private void configrecycler()
    {
        favorite_adpter.setData(goods_beans);
        favorite_adpter.notifyDataSetChanged();
    }
    private void ininitsession()
    {
        btnMyfavoriteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
