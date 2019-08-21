package com.example.latte_core.app;

/**
 * Author: WuYang
 * Date:2019/8/6
 * Description:
 */

/**
 * 枚举类在整个应用程序中是唯一的单例，只能被初始化一次，多线程操作可以保证线程安全
 */
public enum ConfigType {
    API_HOST,//网络请求域名
    APPLICATION_CONTEXT,//
    CONFIG_READY,//初始化完成了没有
    ICON


}
