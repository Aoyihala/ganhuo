package com.p2p.dsad.ganhuo.db;

import com.p2p.dsad.ganhuo.application.MyAppliction;
import com.p2p.dsad.ganhuo.db.bean.DaoMaster;
import com.p2p.dsad.ganhuo.db.bean.DaoSession;

/**
 * 初始化数据库
 * Created by dsad on 2017/9/18.
 */

public class GreenDaoUtil
{


    private GreenDaoUtil()
    {

    }
    public static DaoSession getdaosession()
    {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyAppliction.getmContext(), "notes-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }


}
