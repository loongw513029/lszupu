package com.lon2017.lszupu.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import Presenter.BasePresenter;
import Utils.ActivityManager;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    public AppCompatActivity mActivity;
    protected Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ActivityManager.getInstance().addActivity(this);
        mPresenter = getPresenter();
        gson=new Gson();

    }

    protected abstract P getPresenter();
}
