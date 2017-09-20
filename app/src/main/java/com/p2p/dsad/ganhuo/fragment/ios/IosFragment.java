package com.p2p.dsad.ganhuo.fragment.ios;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.p2p.dsad.ganhuo.GoodActivity;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.adpter.GoodsAdpter;
import com.p2p.dsad.ganhuo.api.GanApi;
import com.p2p.dsad.ganhuo.application.MyAppliction;
import com.p2p.dsad.ganhuo.base.BaseFragment;
import com.p2p.dsad.ganhuo.bean.GoodsBean;
import com.p2p.dsad.ganhuo.bean.PicBean;
import com.p2p.dsad.ganhuo.bean.ResultsBean;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.WomanBean;
import com.p2p.dsad.ganhuo.utlis.ConnectionUtils;
import com.p2p.dsad.ganhuo.utlis.SpUtils;
import com.p2p.dsad.ganhuo.utlis.SpaceRecyclerItem;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 *
 * Created by dsad on 2017/9/13.
 */

public class IosFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.recycler_base)
    RecyclerView recyclerBase;
    @Bind(R.id.refresh_base)
    SwipeRefreshLayout refreshBase;
    private String TAG = "data";
    private String normalurl;
    private String imgurl;
    private String goodresult;
    private String imgresult;
    private GoodsBean normaldata;
    private WomanBean imgdata;
    private int count = Integer.parseInt(GanApi.DEFAUT_LISTSIZE);
    private int page = Integer.parseInt(GanApi.DEFAUT_PAGE);
    private GoodsAdpter good_adpter;
    public static final String IOS_ID="ios";
    private List<PicBean> bean = new ArrayList<>();
    @Override
    public int getLayoutId() {
        //设置默认可见

        return R.layout.fragment_ios;
    }
    @Override
    protected void inintdata()
    {
        setrefresh();
        //初始化一下recycler
        inintrecycler();
        //从服务器获取数据
        getDataFromSever();
    }

    private void setrefresh()
    {
        refreshBase.setRefreshing(true);
        refreshBase.setOnRefreshListener(this);
    }

    private void inintrecycler()
    {
        good_adpter = new GoodsAdpter(null);
        good_adpter.setOnItemClickListener(new GoodsAdpter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(ResultsBean bean) {
                Intent intent = new Intent(mContext, GoodActivity.class);
                intent.putExtra("result",bean);
                startActivity(intent);
            }
        });
        recyclerBase.setLayoutManager(new LinearLayoutManager(MyAppliction.getmContext()));
        recyclerBase.addItemDecoration(new SpaceRecyclerItem(16));
        recyclerBase.setAdapter(good_adpter);
    }
    public void getDataFromSever() {
        page= SpUtils.getInteger(GanApi.SP_GAN_NAME,IOS_ID);
        if (page==0)
        {
            page= Integer.parseInt(GanApi.DEFAUT_PAGE);
        }
        normalurl = ConnectionUtils.getCategoryUrls(GanApi.IOS,String.valueOf(count),String.valueOf(page));
        imgurl = ConnectionUtils.getCategoryUrls(GanApi.FULI,String.valueOf(count),String.valueOf(page));
        //物品
        ConnectionUtils.getData(normalurl, new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }
            @Override
            public void onResponse(String response, int id)
            {
                //清空url,为下拉刷新准备
                normalurl = null;
                //处理下html代码
                goodresult = response;
                //图片(在上一个链接的回调里处理加载图片的json)
                ConnectionUtils.getData(imgurl, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {

                    }

                    @Override
                    public void onResponse(String response, int id)
                    {
                        //清空url
                        imgurl = null;
                        imgresult = response;
                        praseData();
                    }
                });

            }
        });
    }
    private void praseData()
    {
        normaldata = JSON.parseObject(goodresult,GoodsBean.class);
        imgdata = JSON.parseObject(imgresult,WomanBean.class);
        //设置recyclerview
        configrecyclerview();
    }

    private void configrecyclerview()
    {
        good_adpter.setData(normaldata,imgdata);
        good_adpter.notifyDataSetChanged();
        //停止刷新
        if (refreshBase.isRefreshing())
        {
            refreshBase.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh()
    {
        //下拉是翻页刷新的方法后的20条,api限制不要写底部加载更多
        page=page+1;
        //测试
        //count=count+20;
        SpUtils.setInteger(GanApi.SP_GAN_NAME,IOS_ID,page);
        //更新信息
        getDataFromSever();
    }
}
