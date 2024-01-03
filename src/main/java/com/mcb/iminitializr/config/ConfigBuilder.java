package com.mcb.iminitializr.config;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class ConfigBuilder {
    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSourceConfig;

    /**
     * 策略模式
     */
    private StrategyConfig strategyConfig;

    public ConfigBuilder(GlobalConfig globalConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig) {
        this.globalConfig = globalConfig;
        this.dataSourceConfig = dataSourceConfig;
        this.strategyConfig = strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }
}
