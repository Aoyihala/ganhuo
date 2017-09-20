package com.p2p.dsad.ganhuo.fragment.restvideo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 休息视频
 * Created by dsad on 2017/9/15.
 */

public class RestVideo implements Serializable
{

    /**
     * count : 1
     * error : false
     * results : [{"desc":"好甜的《whistle》","ganhuo_id":"56cc6d1d421aa95caa707544","publishedAt":"2015-10-21T02:57:40.914000","readability":"","type":"休息视频","url":"http://video.weibo.com/show?fid=1034:1012bb59b28c2d58e2e9f71968de8c01","who":"lxxself"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results)
    {
        this.results = results;
    }
}
