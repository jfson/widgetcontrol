package com.jfson.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description: 控件生命周期控制中心
 */
public class WidgetControl implements Handler.Callback {
    public static final String TAG = WidgetControl.class.getSimpleName();
    private static final int REMOVE_FRAGMENT = 1;
    private static final int REMOVE_SUPPORT_FRAGMENT = 2;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static final String FRAGMENT_TAG = "com.jfson.atry.te";
    final Map<FragmentManager, SupportLifeFragment> mSupportLifeFragments =
            new HashMap<>();
    final Map<android.app.FragmentManager, LifeFragment> mLifeFragments =
            new HashMap<>();
    private ErrorLifeManager mErrorLifeManager = new ErrorLifeManager();

    private static WidgetControl globalSingletonInstance = null;

    public static WidgetControl getInstance() {
        if (globalSingletonInstance == null) {
            globalSingletonInstance = SingletonHolder.INSTANCE;
        }
        return globalSingletonInstance;
    }

    private static class SingletonHolder {
        private static final WidgetControl INSTANCE = new WidgetControl();
    }

    public LifeManager get(Context context) {
        if (context == null) {
            return mErrorLifeManager;
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        // ApplicationContext 不支持
        return mErrorLifeManager;
    }

    public LifeManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            if (assertNotDestroyed(activity)) {
                return mErrorLifeManager;
            }
            FragmentManager fm = activity.getSupportFragmentManager();
            return supportFragmentGet(fm);
        }
    }

    public LifeManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            android.app.FragmentManager fm = activity.getFragmentManager();
            return fragmentGet(fm);
        }
    }

    private LifeManager supportFragmentGet(FragmentManager fm) {
        SupportLifeFragment current = getSupportFragment(fm);
        LifeManager lifeManager = current.getLifeManager();
        if (lifeManager == null) {
            lifeManager = new LifeManager();
            current.setLifeManager(new LifeManager());
        }
        return lifeManager;
    }

    private LifeManager fragmentGet(android.app.FragmentManager fm) {
        LifeFragment current = getFragment(fm);
        LifeManager lifeManager = current.getLifeManager();
        if (lifeManager == null) {
            lifeManager = new LifeManager();
            current.setLifeManager(lifeManager);
        }
        return lifeManager;
    }

    private SupportLifeFragment getSupportFragment(final FragmentManager fm) {
        SupportLifeFragment current = (SupportLifeFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = mSupportLifeFragments.get(fm);
            if (current == null) {
                current = new SupportLifeFragment();
                mSupportLifeFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(REMOVE_SUPPORT_FRAGMENT, fm).sendToTarget();
            }
        }
        return current;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private LifeFragment getFragment(final android.app.FragmentManager fm) {
        LifeFragment current = (LifeFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = mLifeFragments.get(fm);
            if (current == null) {
                current = new LifeFragment();
                mLifeFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(REMOVE_FRAGMENT, fm).sendToTarget();
            }
        }
        return current;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean assertNotDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case REMOVE_FRAGMENT:
                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
                key = fm;
                removed = mLifeFragments.remove(fm);
                break;
            case REMOVE_SUPPORT_FRAGMENT:
                FragmentManager supportFm = (FragmentManager) message.obj;
                key = supportFm;
                removed = mSupportLifeFragments.remove(supportFm);
                break;
            default:
                handled = false;
                break;
        }
        if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
        }
        return handled;
    }
}
