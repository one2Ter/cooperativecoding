package com.lixinyu.cooperativecoding.config;

public class Config {
    /*
    * 是否启用沙箱编译
    * 启用(推荐): 使用docker环境编译运行代码 编译时间更长 与本机隔离 避免恶意代码引起系统卡顿
    * 不启用:直接使用服务器本机环境进行编译 速度更快 死循环代码引起内存泄漏
    * */
    public static final boolean dockerEnabled = true;
}
