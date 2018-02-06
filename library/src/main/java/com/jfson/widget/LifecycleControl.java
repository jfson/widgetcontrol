package com.jfson.widget;

/**
 * Author: sunzhen
 * Create on:  2018/1/17
 * Description:
 */

public interface LifecycleControl {

    /**
     * 添加当前View的listener,需要生命周期
     */
    void addListener(LifecycleListener listener);


    /**
     * 移除当前View的Listener，不在需要生命周期
     */
    void removeListener(LifecycleListener listener);
}
