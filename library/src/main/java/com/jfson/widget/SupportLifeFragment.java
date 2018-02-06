package com.jfson.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description:空白Fragment，仅仅用来生命周期回调
 */
@SuppressLint("ValidFragment")
public class SupportLifeFragment extends Fragment {
    private LifeManager mLifeManager;


    public SupportLifeFragment() {
        this(new LifeManager());
    }

    @SuppressLint("ValidFragment")
    public SupportLifeFragment(LifeManager manager) {
        this.mLifeManager = manager;
    }

    public LifeManager getLifeManager() {
        return this.mLifeManager;
    }

    public void setLifeManager(LifeManager manager) {
        mLifeManager = manager;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLifeManager != null) {
            mLifeManager.onCreate();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mLifeManager != null) {
            mLifeManager.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLifeManager != null) {
            mLifeManager.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLifeManager != null) {
            mLifeManager.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mLifeManager != null) {
            mLifeManager.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLifeManager != null) {
            mLifeManager.onDestroy();
        }
    }


}
