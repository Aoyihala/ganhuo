package com.p2p.dsad.ganhuo.utlis;

import android.content.Context;
import android.content.SharedPreferences;

import com.p2p.dsad.ganhuo.application.MyAppliction;

/**
 * 缓存页码,利用sp
 * Created by dsad on 2017/9/18.
 */

public class SpUtils
{
    private static SharedPreferences sp;
    private SpUtils()
    {

    }

    /**
     * 存入string
     * @param name
     * @param key
     * @param value
     */
    public static void setString(String name,String key,String value)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key,value);
        ed.commit();
    }

    /**
     * 存入int
     * @param name
     * @param key
     * @param value
     */
    public static void setInteger(String name,String key,int value)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key,value);
        ed.commit();
    }

    /**
     * 存入blooean
     * @param name
     * @param key
     * @param value
     */
    public static void setBlooean(String name,String key,boolean value)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key,value);
        ed.commit();
    }

    public static String getString(String name,String key)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
    public static Boolean getBooean(String name,String key)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
    public static int getInteger(String name,String key)
    {
        sp = MyAppliction.getmContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }
}
