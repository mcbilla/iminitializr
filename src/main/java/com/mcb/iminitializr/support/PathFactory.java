package com.mcb.iminitializr.support;


public interface PathFactory<T> {

    /**
     * 获取枚举对应文件的绝对路径
     * @param t
     * @return
     */
    String getPath(T t);

    /**
     * 获取枚举对应文件的包名
     * @param t
     * @return
     */
    String getPackage(T t);
}
