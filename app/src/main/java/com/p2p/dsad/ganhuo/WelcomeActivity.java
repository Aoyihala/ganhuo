package com.p2p.dsad.ganhuo;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.p2p.dsad.ganhuo.base.BaseActivity;
import com.p2p.dsad.ganhuo.utlis.TosatUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks
{

    @Bind(R.id.img_wel_come)
    ImageView imgWelCome;
    private int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        inintdata();
    }
    private void inintdata()
    {
        //先申请权限
        requestSomePermissions();

    }

    private void requestSomePermissions()
    {
        //声明权限
        String[] permisss = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS
        ,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
        ,Manifest.permission.READ_PHONE_STATE};
        //判断是否申请
        if (!EasyPermissions.hasPermissions(getApplicationContext(),permisss))
        {
            //没有申请
            EasyPermissions.requestPermissions(this,getString(R.string.app_name),num,permisss);
        }
        else
        {
            configanmaition();
        }


    }

    private void configanmaition()
    {
        AlphaAnimation ani = new AlphaAnimation(0,1);
        ani.setDuration(2000);
        ani.start();
        //设置动画
        imgWelCome.setAnimation(ani);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startAcctivity(HomeActivity.class,true);
            }
        },2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms)
    {
        configanmaition();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms)
    {
        configanmaition();
        TosatUtils.ShowToast("权限缺失 影响使用");
    }
}
