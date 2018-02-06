package com.jfson.widgettest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.jfson.widget.LifecycleListener;
import com.jfson.widget.WidgetControl;

/**
 * Author: sunzhen
 * Create on:  2018/2/6
 * Description:
 */
@SuppressLint("AppCompatCustomView")
public class Test extends TextView implements LifecycleListener {
    public Test(Context context) {
        super(context);
        init();
    }

    public Test(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Test(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WidgetControl.getInstance().get(getContext()).addListener(this);
    }

    @Override
    public void onCreate() {
        Log.d("--->", "onCreate() ");
    }

    @Override
    public void onStart() {
        Log.d("--->", "onStart() ");
    }

    @Override
    public void onResume() {
        Log.d("--->", "onResume() ");
    }

    @Override
    public void onPause() {
        Log.d("--->", "onPause() ");
    }

    @Override
    public void onStop() {
        Log.d("--->", "onStop() ");
    }

    @Override
    public void onDestroy() {
        Log.d("--->", "onDestroy() ");
        WidgetControl.getInstance().get(getContext()).removeListener(this);
    }
}
