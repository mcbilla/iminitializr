package com.mcb.iminitializr.constant;

public enum PathEnum {
    // 项目根目录，以下的路径都是基于根目录
    root,
    // src/main/java
    java,
    // src/main/resource
    resource,
    // src/main/java/com/mcb
    // com.mcb
    pkg,
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
    entity
}
