package com.jfson.widget;

import android.util.Log;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description:
 */
public class ErrorLifeManager extends LifeManager{
    @Override
    public void addListener(LifecycleListener listener) {
        Log.d("ErrorLifeManager","error");
//        throw new IllegalStateException("context 不合法！！！");
    }
}
