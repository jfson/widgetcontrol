package com.jfson.widget;

import android.os.Looper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description:
 */
public class Util {
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    public static <T> List<T> getSnapshot(Collection<T> other) {
        // toArray creates a new ArrayList internally and this way we can guarantee entries will not
        // be null.
        List<T> result = new ArrayList<T>(other.size());
        for (T item : other) {
            result.add(item);
        }
        return result;
    }

}
