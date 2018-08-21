package com.oldhigh.learnmeterialedittext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());

        initView();
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     *  获取布局的layout
     * @return
     */
    protected abstract int layoutResId() ;



}
