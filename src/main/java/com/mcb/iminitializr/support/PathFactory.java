package com.mcb.iminitializr.support;


public interface PathFactory<T> {

    /**
     * 获取绝对路径
     * @param t
     * @return
     */
    String getAbsolutePath(T t);

    /**
     * 获取相对于根目录的相对路径
     * @param t
     * @return
     */
    String getRelativePath(T t);

    /**
     * 获取包名
     * @param t
     * @return
     */
    String getPackage(T t);
}
