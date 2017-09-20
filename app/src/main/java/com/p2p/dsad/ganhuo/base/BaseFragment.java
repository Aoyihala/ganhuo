package com.p2p.dsad.ganhuo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.p2p.dsad.ganhuo.R;
import com.p2p.dsad.ganhuo.api.GanApi;

import butterknife.ButterKnife;

/**
 * 所有fragment的基类
 * 为了节约性能 实现fragment懒加载
 * Created by dsad on 2017/9/13.
 */

public abstract class BaseFragment extends Fragment
{
    protected AppCompatActivity mActivity;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) 
    {
        View view = inflater.inflate(getLayoutId(),null);
        ButterKnife.bind(this,view);
        return view;
    }

    /**
     * 视图创建完毕时
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    //懒加载
    private void lazyload()
    {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) 
    {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
        mContext = getContext();
        inintdata();

    }



    protected abstract void inintdata();

    //提供给子类
    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
