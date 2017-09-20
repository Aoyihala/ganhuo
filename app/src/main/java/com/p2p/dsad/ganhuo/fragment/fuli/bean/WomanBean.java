package com.p2p.dsad.ganhuo.fragment.fuli.bean;

import java.util.List;

/**
 * 福利实体
 * Created by dsad on 2017/9/14.
 */

public class WomanBean
{

    /**
     * count : 1
     * error : false
     * results : [{"desc":"11.3","ganhuo_id":"56cc6d1d421aa95caa7075b1","publishedAt":"2015-11-03T06:04:59.454000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1exng5dd728j20go0m877n.jpg","who":"张涵宇"}]
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

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }
}
