package com.mcb.iminitializr.extension.impl;

import com.mcb.iminitializr.extension.ExtensionHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DtoHandlerimpl implements ExtensionHandler {
    @NotNull
    @Override
    public String getTemplateName() {
        return "1111";
    }

    @Override
    public Map<String, Object> getObjectMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("aaa", 111);
        return map;
    }

    @NotNull
    @Override
    public String getOutputFileName() {
        return "2222";
    }

    @NotNull
    @Override
    public String getOutputFilePath() {
        return "3333";
    }
}
