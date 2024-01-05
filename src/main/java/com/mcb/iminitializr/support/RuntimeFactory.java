package com.mcb.iminitializr.support;


import com.mcb.iminitializr.constant.PathEnum;

public interface RuntimeFactory {

    /**
     * 获取绝对路径
     * @param pathEnum
     * @return
     */
    String getAbsolutePath(PathEnum pathEnum);

    /**
     * 获取相对于根目录的相对路径
     * @param pathEnum
     * @return
     */
    String getRelativePath(PathEnum pathEnum);

    /**
     * 获取包名
     * @param pathEnum
     * @return
     */
    String getPackage(PathEnum pathEnum);

    Object getData(String key);
}
