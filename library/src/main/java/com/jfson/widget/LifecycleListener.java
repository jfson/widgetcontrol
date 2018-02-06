package com.jfson.widget;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description: 回调
 */
public interface LifecycleListener {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
