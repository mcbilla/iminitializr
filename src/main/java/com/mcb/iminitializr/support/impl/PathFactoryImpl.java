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
        this.pathMap.put(PathEnum.root, rootPath);
        this.pathMap.put(PathEnum.java, getPath(PathEnum.root) + Constant.JAVA_ROOT_PATH);
        this.pathMap.put(PathEnum.resource, getPath(PathEnum.root) + Constant.RESOURCE_ROOT_PATH);
        this.pathMap.put(PathEnum.pkg, getPath(PathEnum.java) + packagePath);
        this.pathMap.put(PathEnum.test_java, getPath(PathEnum.root) + Constant.TEST_JAVA_ROOT_PATH);
        this.pathMap.put(PathEnum.test_resource, getPath(PathEnum.root) + Constant.TEST_RESOURCE_ROOT_PATH);
        this.pathMap.put(PathEnum.test_pkg, getPath(PathEnum.test_java) + packagePath + Constant.TEST_PATH);
    }

    private void handlePackageMap() {
        this.packageMap.put(PathEnum.pkg, pathToPackage(this.pathMap.get(PathEnum.pkg)));
        this.packageMap.put(PathEnum.test_pkg, pathToPackage(this.pathMap.get(PathEnum.test_pkg)));
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
