package com.p2p.dsad.ganhuo.fragment.restvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.VideoActivity;
import com.p2p.dsad.ganhuo.adpter.RestVideoAdpter;
import com.p2p.dsad.ganhuo.api.GanApi;
import com.p2p.dsad.ganhuo.base.BaseFragment;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.RestVideo;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.ResultsBean;
import com.p2p.dsad.ganhuo.utlis.ConnectionUtils;
import com.p2p.dsad.ganhuo.utlis.SpUtils;
import com.p2p.dsad.ganhuo.utlis.SpaceRecyclerItem;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 休息视频
 * Created by dsad on 2017/9/13.
 */

public class RestVideoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_base)
    RecyclerView recyclerBase;
    @Bind(R.id.refresh_base)
    SwipeRefreshLayout refreshBase;
    private RestVideoAdpter rest_adpter;
    private String url;
    private RestVideo video_data;
    private int count = Integer.parseInt(GanApi.DEFAUT_LISTSIZE);
    private int page = Integer.parseInt(GanApi.DEFAUT_PAGE);
    private String reuslt;
    public static final String RESTVIDE_ID = "restvideo";
    @Override
    protected void inintdata() {
        setRefresh();
        //初始化recycler
        inintrecycler();
        //获取数据
        getDataFromSever();

    }

    private void setRefresh()
    {
        refreshBase.setRefreshing(true);
        refreshBase.setOnRefreshListener(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_restvideo;
    }

    private void inintrecycler() {
        rest_adpter = new RestVideoAdpter();
        rest_adpter.setOnItemClickListener(new RestVideoAdpter.OnItemClickListener() {
            @Override
            public void OnItemClick(ResultsBean bean) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("video",bean);
                startActivity(intent);
            }
        });
        recyclerBase.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerBase.addItemDecoration(new SpaceRecyclerItem(16));
        recyclerBase.setAdapter(rest_adpter);
    }

    public void getDataFromSever() {
        page= SpUtils.getInteger(GanApi.SP_GAN_NAME,RESTVIDE_ID);
        if (page==0)
        {
            page= Integer.parseInt(GanApi.DEFAUT_PAGE);
        }
        url = ConnectionUtils.getCategoryUrls(GanApi.REST_VIDEO, String.valueOf(count), String.valueOf(page));
        ConnectionUtils.getData(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                reuslt = response;
                parseData();
            }
        });
    }

    private void parseData() {
        video_data = JSON.parseObject(reuslt, RestVideo.class);
        //设置recycler数据
        configrecycler();
    }

    private void configrecycler() {
        rest_adpter.setData(video_data);
        rest_adpter.notifyDataSetChanged();
        if (refreshBase.isRefreshing())
        {
            refreshBase.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        page = page + 1;
        SpUtils.setInteger(GanApi.SP_GAN_NAME,RESTVIDE_ID,page);
        getDataFromSever();
    }


}
