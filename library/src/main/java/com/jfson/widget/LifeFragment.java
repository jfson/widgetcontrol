package com.jfson.widget;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description:
 */
public class LifeFragment extends Fragment{
    private LifeManager mLifeManager;


    public LifeFragment() {
        this(new LifeManager());
    }

    @SuppressLint("ValidFragment")
    public LifeFragment(LifeManager manager) {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
