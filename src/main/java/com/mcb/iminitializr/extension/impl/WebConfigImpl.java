package com.mcb.iminitializr.extension.impl;

import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.extension.RuntimeFactoryAware;
import com.mcb.iminitializr.support.RuntimeFactory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebConfigImpl implements ExtensionHandler, RuntimeFactoryAware {
    private RuntimeFactory runtimeFactory;

    @Override
    public void setPathFactory(RuntimeFactory factory) {
        this.runtimeFactory = factory;
    }

    @Override
    public @NotNull String getTemplateName() {
        return "config.java";
    }

    @Override
    public @NotNull Map<String, Object> getObjectMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("packageName", runtimeFactory.getPackage(PathEnum.pkg) + ".config");
        objectMap.put("className", getOutputFileName());
        objectMap.put("interceptorName", runtimeFactory.getPackage(PathEnum.pkg) + ".interceptor." + new InterceptorImpl().getOutputFileName());
        objectMap.put("filterName", runtimeFactory.getPackage(PathEnum.pkg) + ".filter." + new FilterImpl().getOutputFileName());

        return objectMap;
    }

    @Override
    public @NotNull String getOutputFileName() {
        return "WebConfig";
    }

    @Override
    public @NotNull String getOutputFilePath() {
        return this.runtimeFactory.getRelativePath(PathEnum.pkg) + "/config";
    }
}
