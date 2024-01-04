package com.mcb.iminitializr.support.impl;

import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.constant.Constant;
import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.support.PathFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PathFactoryImpl implements PathFactory<PathEnum> {
    private Map<PathEnum, String> pathMap = new HashMap<>();

    private Map<PathEnum, String> packageMap = new HashMap<>();

    public PathFactoryImpl(GlobalConfig globalConfig) {
        initPath(globalConfig);
    }

    public void initPath(GlobalConfig globalConfig) {
        String groupId = globalConfig.getGroupId();
        String artifactId = globalConfig.getArtifactId();
        // 获取根路径
        String rootPath = globalConfig.getOutputDir() + File.separator + artifactId;
        // 获取包值，com.mcb
        String packageVal = groupId + Constant.DOT + artifactId.replace(Constant.DASH, Constant.DOT);
        // 获取包路径，/com/mcb
        String packagePath = File.separator + packageVal.replace(Constant.DOT, File.separator);

        handlePathMap(rootPath, packagePath);
        handlePackageMap();
    }

    private void handlePathMap(String rootPath, String packagePath) {
        // 项目基本路径
        this.pathMap.put(PathEnum.root, rootPath);
        this.pathMap.put(PathEnum.java, getPath(PathEnum.root) + Constant.JAVA_ROOT_PATH);
        this.pathMap.put(PathEnum.resource, getPath(PathEnum.root) + Constant.RESOURCE_ROOT_PATH);
        this.pathMap.put(PathEnum.pkg, getPath(PathEnum.java) + packagePath);
        this.pathMap.put(PathEnum.test_java, getPath(PathEnum.root) + Constant.TEST_JAVA_ROOT_PATH);
        this.pathMap.put(PathEnum.test_resource, getPath(PathEnum.root) + Constant.TEST_RESOURCE_ROOT_PATH);
        this.pathMap.put(PathEnum.test_pkg, getPath(PathEnum.test_java) + packagePath + Constant.TEST_PATH);

        // MVC层路径
        this.pathMap.put(PathEnum.controller, getPath(PathEnum.pkg) + Constant.CONTROLLER_PATH);
        this.pathMap.put(PathEnum.service, getPath(PathEnum.pkg) + Constant.SERVICE_PATH);
        this.pathMap.put(PathEnum.serviceImpl, getPath(PathEnum.pkg) + Constant.SERVICE_IMPL_PATH);
        this.pathMap.put(PathEnum.mapper, getPath(PathEnum.pkg) + Constant.MAPPER_PATH);
        this.pathMap.put(PathEnum.xml, getPath(PathEnum.resource) + Constant.XML_PATH);
        this.pathMap.put(PathEnum.entity, getPath(PathEnum.pkg) + Constant.ENTITY_PATH);

        // 项目扩展路径
        this.pathMap.put(PathEnum.config, getPath(PathEnum.pkg) + Constant.CONFIG_PATH);
        this.pathMap.put(PathEnum.interceptor, getPath(PathEnum.pkg) + Constant.INTERCEPTOR_PATH);
        this.pathMap.put(PathEnum.dto, getPath(PathEnum.pkg) + Constant.DTO_PATH);
        this.pathMap.put(PathEnum.global, getPath(PathEnum.pkg) + Constant.GLOBAL_PATH);
        this.pathMap.put(PathEnum.constant, getPath(PathEnum.pkg) + Constant.CONSTANT_PATH);
        this.pathMap.put(PathEnum.exception, getPath(PathEnum.pkg) + Constant.EXCEPTION_PATH);
    }

    private void handlePackageMap() {
        this.packageMap.put(PathEnum.pkg, pathToPackage(this.pathMap.get(PathEnum.pkg)));
        this.packageMap.put(PathEnum.test_pkg, pathToPackage(this.pathMap.get(PathEnum.test_pkg)));

        this.packageMap.put(PathEnum.controller, pathToPackage(this.pathMap.get(PathEnum.controller)));
        this.packageMap.put(PathEnum.service, pathToPackage(this.pathMap.get(PathEnum.service)));
        this.packageMap.put(PathEnum.serviceImpl, pathToPackage(this.pathMap.get(PathEnum.serviceImpl)));
        this.packageMap.put(PathEnum.mapper, pathToPackage(this.pathMap.get(PathEnum.mapper)));
        this.packageMap.put(PathEnum.entity, pathToPackage(this.pathMap.get(PathEnum.entity)));

        this.packageMap.put(PathEnum.config, pathToPackage(this.pathMap.get(PathEnum.config)));
        this.packageMap.put(PathEnum.interceptor, pathToPackage(this.pathMap.get(PathEnum.interceptor)));
        this.packageMap.put(PathEnum.dto, pathToPackage(this.pathMap.get(PathEnum.dto)));
        this.packageMap.put(PathEnum.global, pathToPackage(this.pathMap.get(PathEnum.global)));
        this.packageMap.put(PathEnum.constant, pathToPackage(this.pathMap.get(PathEnum.constant)));
        this.packageMap.put(PathEnum.exception, pathToPackage(this.pathMap.get(PathEnum.exception)));
    }

    private String pathToPackage(String s) {
        // /root/src/main/java/com/mcb/xxx -> com/mcb/xxx
        String pkg = s.substring(getPath(PathEnum.java).length() + 1);
        // com/mcb/xxx -> com.mcb.xxx
        return pkg.replaceAll(File.separator, Constant.DOT);
    }

    @Override
    public String getPath(PathEnum path) {
        return pathMap.get(path);
    }

    @Override
    public String getPackage(PathEnum pathEnum) {
        return packageMap.get(pathEnum);
    }
}
