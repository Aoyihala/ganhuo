package com.p2p.dsad.ganhuo.fragment.restvideo.bean;

import java.io.Serializable;

/**
 * 休息视频的实体
 * Created by dsad on 2017/9/15.
 */

public class ResultsBean implements Serializable{
    /**
     * desc : 好甜的《whistle》
     * ganhuo_id : 56cc6d1d421aa95caa707544
     * publishedAt : 2015-10-21T02:57:40.914000
     * readability :
     * type : 休息视频
     * url : http://video.weibo.com/show?fid=1034:1012bb59b28c2d58e2e9f71968de8c01
     * who : lxxself
     */

    private String desc;
    private String ganhuo_id;
    private String publishedAt;
    private String readability;
    private String type;
    private String url;
    private String who;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGanhuo_id() {
        return ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
