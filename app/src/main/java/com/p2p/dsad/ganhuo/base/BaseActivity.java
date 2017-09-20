package com.p2p.dsad.ganhuo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 所有activity的基类
 * Created by dsad on 2017/9/12.
 */
public class BaseActivity extends AppCompatActivity
{
    private ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void startAcctivity(Class clzz,boolean flag)
    {
        if (flag)
        {
            startActivity(new Intent(BaseActivity.this,clzz));
            finish();
        }
        else
        {
            startActivity(new Intent(BaseActivity.this,clzz));
        }
    }
    /**
     * 显示进度条对话框
     * @param msg
     */
    protected void showProgressDialog(String msg){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    /**
     * 取消进度条对话框
     */
    protected void cancelProgressDialog(){

        if( progressDialog!=null){
            progressDialog.dismiss();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( progressDialog!=null){
            progressDialog.dismiss();
        }
    }

}
