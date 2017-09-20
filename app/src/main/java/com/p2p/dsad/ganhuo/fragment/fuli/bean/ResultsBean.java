package com.p2p.dsad.ganhuo.fragment.fuli.bean;

import java.io.Serializable;

/**
 *
 * Created by dsad on 2017/9/14.
 */

public class ResultsBean implements Serializable
{
    /**
     * desc : 11.3
     * ganhuo_id : 56cc6d1d421aa95caa7075b1
     * publishedAt : 2015-11-03T06:04:59.454000
     * readability :
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1exng5dd728j20go0m877n.jpg
     * who : 张涵宇
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
