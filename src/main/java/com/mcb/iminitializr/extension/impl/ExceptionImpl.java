package com.mcb.iminitializr.extension.impl;

import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.extension.RuntimeFactoryAware;
import com.mcb.iminitializr.support.RuntimeFactory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ExceptionImpl implements ExtensionHandler, RuntimeFactoryAware {

    private RuntimeFactory runtimeFactory;

    @Override
    public void setPathFactory(RuntimeFactory factory) {
        this.runtimeFactory = factory;
    }

    @Override
    public @NotNull String getTemplateName() {
        return "exception.java";
    }

    @Override
    public @NotNull Map<String, Object> getObjectMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("packageName", runtimeFactory.getPackage(PathEnum.pkg) + ".exception");
        objectMap.put("className", getOutputFileName());
        return objectMap;
    }

    @Override
    public @NotNull String getOutputFileName() {
        return "BusnessException";
    }

    @Override
    public @NotNull String getOutputFilePath() {
        return this.runtimeFactory.getRelativePath(PathEnum.pkg) + "/exception";
    }

}
