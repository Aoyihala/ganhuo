package com.p2p.dsad.ganhuo.utlis;

import android.content.Context;
import android.widget.Toast;

import com.p2p.dsad.ganhuo.application.MyAppliction;

/**
 * 弹窗工具,减少消耗
 * Created by My on 2017/6/14.
 */
public class TosatUtils
{
    private static Toast toast;
    private TosatUtils()
    {

    }
    public static void ShowToast(String str)
    {
        if (toast==null)
        {
            //第一次创建
           toast= Toast.makeText(MyAppliction.getmContext(),str, Toast.LENGTH_SHORT);
        }
        else
        {
            toast.setText(str);
        }
        toast.show();
    }
}
