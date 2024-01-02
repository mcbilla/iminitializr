package com.mcb.iminitializr.config;

public class ConfigBuilder {
    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    public ConfigBuilder(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }
}
