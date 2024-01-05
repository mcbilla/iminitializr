package com.mcb.iminitializr.support.impl;

import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.constant.Constant;
import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.support.PathFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PathFactoryImpl implements PathFactory<PathEnum> {
    private Map<PathEnum, String> absolutePathMap = new HashMap<>();

    private Map<PathEnum, String> relativePathMap = new HashMap<>();

    private Map<PathEnum, String> packageMap = new HashMap<>();

    private String rootPath;

    private String packageName;

    private String packagePath;

    public PathFactoryImpl(GlobalConfig globalConfig) {
        String groupId = globalConfig.getGroupId();
        String artifactId = globalConfig.getArtifactId();
        // 获取根路径
        this.rootPath = globalConfig.getOutputDir() + File.separator + artifactId;
        // 获取包值，com.mcb
        this.packageName = groupId + Constant.DOT + artifactId.replace(Constant.DASH, Constant.DOT);
        // 获取包路径，com/mcb
        this.packagePath = this.packageName.replace(Constant.DOT, File.separator);

        handleAbsolutePathMap();
        handleRelativePathMap();
        handlePackageMap();
    }

    private void handleAbsolutePathMap() {
        this.absolutePathMap.put(PathEnum.root, this.rootPath);
        this.absolutePathMap.put(PathEnum.java, getAbsolutePath(PathEnum.root) + Constant.JAVA_ROOT_PATH);
        this.absolutePathMap.put(PathEnum.resource, getAbsolutePath(PathEnum.root) + Constant.RESOURCE_ROOT_PATH);
        this.absolutePathMap.put(PathEnum.pkg, getAbsolutePath(PathEnum.java) + File.separator + this.packagePath);
        this.absolutePathMap.put(PathEnum.test_java, getAbsolutePath(PathEnum.root) + Constant.TEST_JAVA_ROOT_PATH);
        this.absolutePathMap.put(PathEnum.test_resource, getAbsolutePath(PathEnum.root) + Constant.TEST_RESOURCE_ROOT_PATH);
        this.absolutePathMap.put(PathEnum.test_pkg, getAbsolutePath(PathEnum.test_java) + File.separator + this.packagePath + Constant.TEST_PATH);
    }

    private void handleRelativePathMap() {
        this.relativePathMap.put(PathEnum.pkg, absolutePathToRelative(this.absolutePathMap.get(PathEnum.pkg)));
        this.relativePathMap.put(PathEnum.test_pkg, absolutePathToRelative(this.absolutePathMap.get(PathEnum.test_pkg)));
    }

    private void handlePackageMap() {
        this.packageMap.put(PathEnum.pkg, relativePathToPackage(this.relativePathMap.get(PathEnum.pkg)));
        this.packageMap.put(PathEnum.test_pkg, relativePathToPackage(this.relativePathMap.get(PathEnum.test_pkg)));
    }

    /**
     * /root/src/main/java/com/mcb/xxx -> com/mcb/xxx
     * @param s
     * @return
     */
    private String absolutePathToRelative(String s) {
        return s.substring(getAbsolutePath(PathEnum.java).length() + 1);
    }

    /**
     * com/mcb/xxx -> com.mcb.xxx
     * @param s
     * @return
     */
    private String relativePathToPackage(String s) {
        return s.replaceAll(File.separator, Constant.DOT);
    }

    @Override
    public String getAbsolutePath(PathEnum pathEnum) {
        return absolutePathMap.get(pathEnum);
    }

    @Override
    public String getRelativePath(PathEnum pathEnum) {
        return relativePathMap.get(pathEnum);
    }

    @Override
    public String getPackage(PathEnum pathEnum) {
        return packageMap.get(pathEnum);
    }
}
