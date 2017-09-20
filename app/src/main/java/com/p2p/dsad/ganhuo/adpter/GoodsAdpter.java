package com.p2p.dsad.ganhuo.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.api.MeiTuApi;
import com.p2p.dsad.ganhuo.bean.GoodsBean;
import com.p2p.dsad.ganhuo.bean.ResultsBean;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.WomanBean;
import com.p2p.dsad.ganhuo.utlis.Utils;


/**
 * 一般成列物
 * Created by dsad on 2017/9/14.
 */

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.MyGoodsHolder>
{

    private GoodsBean bean;
    private String url;
    private WomanBean imgbean;
    private OnItemClickListener listener;
    public GoodsAdpter(GoodsBean bean)
    {
        this.bean=bean;
    }
    public void setData(GoodsBean bean,WomanBean imgbean)
    {
        this.bean=bean;
        this.imgbean=imgbean;
    }
    @Override
    public MyGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item,parent,false);
        return new MyGoodsHolder(view);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(MyGoodsHolder holder, int position)
    {

        ResultsBean result = bean.getResults().get(position);
        com.p2p.dsad.ganhuo.fragment.fuli.bean.ResultsBean imgresult = imgbean.getResults().get(position);
        //替换为全1080p的图片,不为用户节约流量(滑稽)(前两个随机)
        //根据情况自设
        Glide.with(holder.itemView.getContext()).load(imgresult.getUrl()).into(holder.img_backgroud);
        holder.img_backgroud.setBackgroundResource(0);
        holder.tv_title.setText(result.getDesc());
        holder.tv_author_time.setText(Utils.getFormatDateStr(Utils.formatDateFromStr(result.getPublishedAt())));
        holder.tv_imgauthor.setText("作者:"+result.getWho());

    }

    @Override
    public int getItemCount()
    {
        //关键更新时这里条件要判断好
        return bean==null?0:bean.getResults().size();
    }

    class MyGoodsHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView img_backgroud;
        private TextView tv_title;
        private TextView tv_author_time;
        private ImageView img_cao;
        private TextView tv_imgauthor;
        public MyGoodsHolder(View itemView)
        {
            super(itemView);
            img_backgroud = itemView.findViewById(R.id.img_item_background);
            img_cao = itemView.findViewById(R.id.img_item_cao);
            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_author_time = itemView.findViewById(R.id.tv_item_auther_time);
            tv_imgauthor = itemView.findViewById(R.id.tv_item_imgauthor);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view)
        {
            if (listener!=null)
            {
                listener.OnItemClickListener(bean.getResults().get(getLayoutPosition()));
            }
        }
    }

    public interface OnItemClickListener
    {
        void OnItemClickListener(ResultsBean bean);
    }
}
