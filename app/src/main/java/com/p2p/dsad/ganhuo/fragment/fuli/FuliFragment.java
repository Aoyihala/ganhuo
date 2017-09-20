package com.p2p.dsad.ganhuo.fragment.fuli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.p2p.dsad.ganhuo.ImgActivity;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.adpter.FuliAdpter;
import com.p2p.dsad.ganhuo.api.GanApi;
import com.p2p.dsad.ganhuo.base.BaseFragment;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.ResultsBean;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.WomanBean;
import com.p2p.dsad.ganhuo.utlis.ConnectionUtils;
import com.p2p.dsad.ganhuo.utlis.PictUtil;
import com.p2p.dsad.ganhuo.utlis.SpUtils;
import com.p2p.dsad.ganhuo.utlis.SpaceRecyclerItem;
import com.p2p.dsad.ganhuo.utlis.TosatUtils;
import com.p2p.dsad.ganhuo.utlis.imgloader.MyFuliBitmapUtil;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 福利
 * Created by dsad on 2017/9/13.
 */

public class FuliFragment extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.recycler_base)
    RecyclerView recyclerBase;
    @Bind(R.id.refresh_base)
    SwipeRefreshLayout refreshBase;
    private WomanBean woman_bean;
    private FuliAdpter fuli_adpter;
    private String url;
    private String result;
    private int page= Integer.parseInt(GanApi.DEFAUT_PAGE);
    private int count = 20;
    private Snackbar bottom_snack;
    private ProgressDialog proess_dialog;
    public final static String FULI_ID="fuli";
    @Override
    protected void inintdata()
    {

        //设置刷新按钮
        configrefresh();
        //初始化recycler
        inintrecycler();
        //从服务器获取数据
        getDataFromSever();

    }
    private void configrefresh()
    {
        refreshBase.setRefreshing(true);
        refreshBase.setOnRefreshListener(this);
    }

    private void inintrecycler()
    {
        fuli_adpter = new FuliAdpter();
        fuli_adpter.setOnLongClickListener(new FuliAdpter.OnLongClickListener() {
            @Override
            public void OnLongItemCick(final ResultsBean bean,View v)
            {
             bottom_snack = Snackbar.make(v,bean.getDesc()+"\t\t提供者:"+bean.getWho(),Snackbar.LENGTH_LONG).
                        setAction("下载", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                //开启进度条
                                proess_dialog = new ProgressDialog(mContext);
                                proess_dialog.setTitle("下载中");
                                proess_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                proess_dialog.show();
                                downloadimage(bean);
                            }
                        });
                bottom_snack.show();
                View snack_v = bottom_snack.getView();
                v.setBackgroundColor(Color.WHITE);
            }
        });
        fuli_adpter.setOnClicListener(new FuliAdpter.OnItemClickListener() {
            @Override
            public void OnItemClick(ResultsBean bean)
            {
                //跳转全屏查看
                Intent intent = new Intent(mContext, ImgActivity.class);
                intent.putExtra("img",bean);
                startActivity(intent);
            }
        });
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,1);
        recyclerBase.setLayoutManager(manager);
        recyclerBase.setAdapter(fuli_adpter);
        recyclerBase.addItemDecoration(new SpaceRecyclerItem(8));
    }

    /**
     * 下载图片
     * @param bean
     */
    private void downloadimage(final ResultsBean bean)
    {
        ConnectionUtils.getData(bean.getUrl(), new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }
            @Override
            public void inProgress(float progress, long total, int id)
            {
                //更新进度条
                proess_dialog.setMax((int) total);
                proess_dialog.setProgress((int) progress/(int) total);
                proess_dialog.setCancelable(false);
                proess_dialog.setProgressNumberFormat("%1d kb/%2d kb");

            }
            @Override
            public void onResponse(Bitmap response, int id)
            {
                //下载完成
               Bitmap mydownloadimg = response;
                try
                {
                    if (mydownloadimg!=null)
                    {
                        if (PictUtil.hasSDCard())
                        {
                            PictUtil.saveToFile(new File(PictUtil.getSavePath(),bean.getGanhuo_id()+".jpeg"),mydownloadimg);
                            proess_dialog.dismiss();
                            Snackbar.make(getView(),"下载完成",Snackbar.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Snackbar.make(getView(),"没有sd卡",Snackbar.LENGTH_SHORT);
                        }

                    }
                    else
                    {
                        TosatUtils.ShowToast("该图片已经.....");
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_fuli;
    }
    public void getDataFromSever()
    {
        page=SpUtils.getInteger(GanApi.SP_GAN_NAME,FULI_ID);
        if (page == 0) {
            page= Integer.parseInt(GanApi.DEFAUT_PAGE);
        }
        url = ConnectionUtils.getCategoryUrls(GanApi.FULI,String.valueOf(count),String.valueOf(page));
        ConnectionUtils.getData(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }
            @Override
            public void onResponse(String response, int id)
            {
                result = response;
                paresData();
            }
        });
    }

    private void paresData()
    {
        woman_bean = JSON.parseObject(result,WomanBean.class);
        //设置recyclerview
        configrecycler();
    }

    private void configrecycler()
    {
        fuli_adpter.setData(woman_bean);
        fuli_adpter.notifyDataSetChanged();
        if (refreshBase.isRefreshing())
        {
            refreshBase.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh()
    {
        page=page+1;
        SpUtils.setInteger(GanApi.SP_GAN_NAME,FULI_ID,page);
        getDataFromSever();
    }
}
