package com.mcb.iminitializr;

import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.extension.RuntimeFactoryAware;
import com.mcb.iminitializr.support.RuntimeFactory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MyExtension implements ExtensionHandler, RuntimeFactoryAware {
    private RuntimeFactory runtimeFactory;

    @Override
    public void setPathFactory(RuntimeFactory factory) {
        this.runtimeFactory = factory;
    }


    @Override
    public @NotNull String getTemplateName() {
        return "myextension.java";
    }

    @Override
    public @NotNull Map<String, Object> getObjectMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("packageName", runtimeFactory.getPackage(PathEnum.pkg) + ".myextension");
        objectMap.put("className", getOutputFileName());
        return objectMap;
    }

    @Override
    public @NotNull String getOutputFileName() {
        return "Myextension";
    }

    @Override
    public @NotNull String getOutputFilePath() {
        return this.runtimeFactory.getRelativePath(PathEnum.pkg) + "/myextension";
    }

    /**
     * 返回false可以禁用插件，默认情况下返回true
     * @return
     */
    @Override
    public boolean enable() {
        return false;
    }
}
