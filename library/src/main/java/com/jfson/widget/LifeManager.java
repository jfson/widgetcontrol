package com.jfson.widget;

import android.os.Handler;
import android.os.Looper;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description: life回调Manager
 */
public class LifeManager implements LifecycleControl {
    private Set<LifecycleListener> mListeners = Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());
    private LifecycleListener temporaryListener;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable safeAddRunable = new Runnable() {
        @Override
        public void run() {
            if (mListeners != null && temporaryListener != null)
                mListeners.add(temporaryListener);
        }
    };

    @Override
    public void addListener(LifecycleListener listener) {
        if (mListeners == null) {
            return;
        }
        if (Util.isOnBackgroundThread()) {
            temporaryListener = listener;
            mainHandler.post(safeAddRunable);
        } else {
            mListeners.add(listener);
        }
    }

    @Override
    public void removeListener(LifecycleListener listener) {
        if (mListeners == null) {
            return;
        }
        mListeners.remove(listener);
    }


    void onCreate() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onCreate();
            }
        }
    }

    void onResume() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onResume();
            }
        }
    }

    void onStart() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onStart();
            }
        }
    }

    void onPause() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onPause();
            }
        }
    }

    void onStop() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onStop();
            }
        }
    }

    void onDestroy() {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(mListeners)) {
            if (lifecycleListener != null) {
                lifecycleListener.onDestroy();
            }
        }

        mainHandler.removeCallbacksAndMessages(null);
        temporaryListener = null;
        mListeners.clear();
        mainHandler = null;
    }
}
