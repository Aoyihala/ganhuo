package com.p2p.dsad.ganhuo.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.p2p.dsad.ganhuo.utlis.Utils;
import com.mukesh.MarkdownView;
import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.RestVideo;
import com.p2p.dsad.ganhuo.fragment.restvideo.bean.ResultsBean;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 *
 * Created by dsad on 2017/9/15.
 */

public class RestVideoAdpter extends RecyclerView.Adapter<RestVideoAdpter.RestVideoHolder>
{

    private WebSettings settings;
    private RestVideo video;
    private OnItemClickListener listener;
    public void setData(RestVideo video)
    {
        this.video=video;
    }
    @Override
    public RestVideoAdpter.RestVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_video,parent,false);
        return new RestVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(RestVideoAdpter.RestVideoHolder holder, int position)
    {
        ResultsBean bean = video.getResults().get(position);

        holder.tv_time.setText(Utils.getFormatDateStr(Utils.formatDateFromStr(bean.getPublishedAt())));
        holder.tv_title.setText(bean.getDesc());
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }

    @Override
    public int getItemCount()
    {
        return video==null?0:video.getCount();
    }

    class RestVideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView tv_title;
        private TextView tv_time;
        public RestVideoHolder(View itemView)
        {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_video_title);
            tv_time = itemView.findViewById(R.id.tv_video_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (listener!=null)
            {
                listener.OnItemClick(video.getResults().get(getLayoutPosition()));
            }
        }
    }

    public interface OnItemClickListener
    {
        void OnItemClick(ResultsBean bean);
    }
}
