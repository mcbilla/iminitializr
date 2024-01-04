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
    // com.mcb
    pkg,
    // src/test/java
    test_java,
    // src/test/resource
    test_resource,
    // src/test/java/com/mcb
    // com.mcb
    test_pkg,

    /*-------------------------------MVC结构路径---------------------------*/
    // src/main/java/com/mcb/controller
    // com.mcb.controller
    controller,
    // src/main/java/com/mcb/service
    // com.mcb.service
    service,
    // src/main/java/com/mcb/service/impl
    // com.mcb.service.impl
    serviceImpl,
    // src/main/java/com/mcb/mapper
    // com.mcb.mapper
    mapper,
    // src/main/resource/mapper
    xml,
    // src/main/java/com/mcb/entity
    // com.mcb.entity
    entity,

    /*-------------------------------项目扩展结构路径---------------------------*/
    // src/main/java/com/mcb/annotation
    // com.mcb.annotation
    config,
    // src/main/java/com/mcb/interceptor
    // com.mcb.interceptor
    interceptor,
    // src/main/java/com/mcb/dto
    // com.mcb.dto
    dto,
    // src/main/java/com/mcb/global
    // com.mcb.global
    global,
    // src/main/java/com/mcb/constant
    // com.mcb.constant
    constant,
    // src/main/java/com/mcb/exception
    // com.mcb.exception
    exception
}
