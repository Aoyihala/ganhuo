package com.p2p.dsad.ganhuo.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.ResultsBean;
import com.p2p.dsad.ganhuo.fragment.fuli.bean.WomanBean;
import com.p2p.dsad.ganhuo.utlis.imgloader.MyFuliBitmapUtil;

/**
 * 福利的adpter
 * Created by dsad on 2017/9/16.
 */

public class FuliAdpter extends RecyclerView.Adapter<FuliAdpter.FuliViewHolde>
{
    private WomanBean bean;
    private MyFuliBitmapUtil util;
    private FuliAdpter.OnItemClickListener listener;
    private OnLongClickListener longClickListener;
    public void setData(WomanBean bean)
    {
        this.bean = bean;
        util = new MyFuliBitmapUtil();
    }
    @Override
    public FuliViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fuli_item,parent,false);
        return new FuliViewHolde(view);
    }

    @Override
    public void onBindViewHolder(FuliViewHolde holder, int position)
    {
        ResultsBean oneitme = bean.getResults().get(position);
        holder.menv.setTag(oneitme.getUrl());
        util.display(oneitme.getUrl(),holder.menv);

        //Glide.with(holder.menv.getContext()).load(oneitme.getUrl()).into(holder.menv);
    }
    public void setOnClicListener(FuliAdpter.OnItemClickListener listener)
    {
        this.listener = listener;
    }
    public void setOnLongClickListener(OnLongClickListener clickListener)
    {
        this.longClickListener = clickListener;
    }
    @Override
    public int getItemCount() {
        return bean==null?0:bean.getCount();
    }

    class FuliViewHolde extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        private ImageView menv;
        public FuliViewHolde(View itemView)
        {
            super(itemView);
            menv = itemView.findViewById(R.id.img_fuli_resource);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View view)
        {
            if (listener!=null)
            {
                listener.OnItemClick(bean.getResults().get(getLayoutPosition()));
            }
        }

        @Override
        public boolean onLongClick(View view)
        {
            if (longClickListener!=null)
            {
                longClickListener.OnLongItemCick(bean.getResults().get(getLayoutPosition()),menv);
            }
            return true;
        }
    }

   public interface OnItemClickListener
    {
        void OnItemClick(ResultsBean bean);
    }
    public interface OnLongClickListener
    {
        void OnLongItemCick(ResultsBean bean,View view);
    }
}
