# widgetcontrol

### 起因
* 项目越来越大,解耦越来越有必要。从架构上解耦mvc到mvp,从功能上解耦和拆分，这就用到了自定义View，自定义View往往也需要用到Activity/Fragment的生命周期。这时候通常做法是在Activity/Fragment中对View进行生命周期的调用透传。

* 如果自定义View的功能逻辑过于复杂后，再拆解出一个MVP层级，Presenter层和View层也需要再重复的透传一层生命周期，这时候就想，能不能一行代码把这些都搞定。所以 WidgetControl 来了，真的只需要一行代码

* 如下图
![image](https://github.com/jfson/ImgResource/blob/master/40.png?raw=true)


### 版本
* v 1.0.1 初始版本
    * 当传入context为 Application context 或者为null时将不会收到回调

### 安装
add dependency to build.gradle 


```
compile 'com.jfson:widget:1.0.1'
```
### 使用

```
 // 该类去实现 LifecycleListener 接口
 WidgetControl.getInstance().get(getContext()).addListener(this);
 
 // 自行remove 或者交由WidgetControl统一处理
 WidgetControl.getInstance().get(getContext()).removeListener(this);
```

### 原理
* 概述
    * a.通过 context 找到该FragmentActivity唯一的FragmentManager
    * b.根据FragmentManager新建唯一的空Fragment，添加至FragmentManager中。
    * c.每个Fragment持有唯一LifeManager,并回调生命周期给向其注册过的View
* 流程图
![流程图](https://github.com/jfson/ImgResource/blob/master/38.png?raw=true)

* 类图
![类图](https://github.com/jfson/ImgResource/blob/master/39.png?raw=true)
