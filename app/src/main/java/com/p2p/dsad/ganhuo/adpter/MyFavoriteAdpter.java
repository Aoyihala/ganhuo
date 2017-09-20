package com.p2p.dsad.ganhuo.adpter;

import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.db.bean.SaveGoodsBeans;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 收藏的adpter
 * Created by dsad on 2017/9/19.
 */

public class MyFavoriteAdpter extends RecyclerView.Adapter<MyFavoriteAdpter.MyFavoriteViewHolder> {



    private List<SaveGoodsBeans> data;
    private OnItmeClickListener listener;
    private OnLongClickListener longClickListener;
    @Override
    public MyFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myfavorite_item, parent, false);
        return new MyFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFavoriteViewHolder holder, int position) {
        SaveGoodsBeans bean = data.get(position);
        holder.tvFavoriteitemTitle.setText("\t\t"+bean.getDesc());
        holder.tvFavoriteitemAuthorname.setText(bean.getAuthor());
        holder.tvFavoriteitemFrom.setText("类型:"+bean.getType());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    public void setOnItemClickListener(OnItmeClickListener listener)
    {
        this.listener = listener;
    }
    public void setOnLongClickListener(OnLongClickListener longClickListener)
    {
        this.longClickListener = longClickListener;
    }
    public void setData(List<SaveGoodsBeans> data) {
        this.data = data;
    }


    class MyFavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        @Bind(R.id.img_favoriteitem_head)
        ImageView imgFavoriteitemHead;
        @Bind(R.id.tv_favoriteitem_authorname)
        TextView tvFavoriteitemAuthorname;
        @Bind(R.id.tv_favoriteitem_title)
        TextView tvFavoriteitemTitle;
        @Bind(R.id.tv_favoriteitem_from)
        TextView tvFavoriteitemFrom;
        public MyFavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (listener!=null)
            {
                listener.onItemClick(data.get(getLayoutPosition()));
            }
        }
        @Override
        public boolean onLongClick(View view)
        {
            return true;
        }
    }

    public interface  OnItmeClickListener
    {
        void onItemClick(SaveGoodsBeans bean);
    }
    public interface OnLongClickListener
    {
        void onLongClick();
    }

}
