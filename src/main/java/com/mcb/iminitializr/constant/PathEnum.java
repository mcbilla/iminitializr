package com.mcb.iminitializr.constant;

/**
 * 项目基本结构
 * 包含目录的绝对路径和包名两部分
 */
public enum PathEnum {
    /*-------------------------------项目基本结构路径---------------------------*/
    // 项目根目录，以下的路径都是基于根目录
    root,
    // src/main/java
    java,
    // src/main/resource
    resource,
    // src/main/java/com/mcb
    // com/mcb
    // com.mcb
    pkg,
    // src/test/java
    test_java,
    // src/test/resource
    test_resource,
    // src/test/java/com/mcb
    // com/mcb
    // com.mcb
    test_pkg,
}
