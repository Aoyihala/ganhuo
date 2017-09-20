package com.p2p.dsad.ganhuo.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 实体类
 * Created by dsad on 2017/9/18.
 */
@Entity
public class SaveGoodsBeans
{
    @Id(autoincrement = true)
    private Long id;
    private String ganhuo_id;
    private String author;
    private String time;
    private String url;
    private String desc;
    private String content;
    private boolean seclect;
    private String type;

    @Generated(hash = 496489795)
    public SaveGoodsBeans(Long id, String ganhuo_id, String author, String time,
            String url, String desc, String content, boolean seclect, String type) {
        this.id = id;
        this.ganhuo_id = ganhuo_id;
        this.author = author;
        this.time = time;
        this.url = url;
        this.desc = desc;
        this.content = content;
        this.seclect = seclect;
        this.type = type;
    }

    @Generated(hash = 1435707581)
    public SaveGoodsBeans() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGanhuo_id() {
        return this.ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getSeclect() {
        return this.seclect;
    }

    public void setSeclect(boolean seclect) {
        this.seclect = seclect;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
