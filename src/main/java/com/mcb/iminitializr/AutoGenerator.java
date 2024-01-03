package com.mcb.iminitializr;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.mcb.iminitializr.config.ConfigBuilder;
import com.mcb.iminitializr.config.DataSourceConfig;
import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.engine.AbstractTemplateEngine;
import com.mcb.iminitializr.engine.FreemarkerTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class AutoGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);

    /**
     * 总体配置信息
     */
    protected ConfigBuilder config;

    private final GlobalConfig.Builder globalConfigBuilder;

    private final DataSourceConfig.Builder dataSourceConfigBuilder;

    private final StrategyConfig.Builder strategyConfigBuilder;

    private AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();

    public AutoGenerator() {
        this.globalConfigBuilder = new GlobalConfig.Builder();
        this.dataSourceConfigBuilder = new DataSourceConfig.Builder();
        this.strategyConfigBuilder = new StrategyConfig.Builder();
    }

    public static AutoGenerator create() {
        return new AutoGenerator();
    }

    public AutoGenerator globalConfig(Consumer<GlobalConfig.Builder> consumer) {
        consumer.accept(this.globalConfigBuilder);
        return this;
    }

    public AutoGenerator dataSourceConfigBuilder(Consumer<DataSourceConfig.Builder> consumer) {
        consumer.accept(this.dataSourceConfigBuilder);
        return this;
    }

    public AutoGenerator strategyConfigBuilder(Consumer<StrategyConfig.Builder> consumer) {
        consumer.accept(this.strategyConfigBuilder);
        return this;
    }

    public void execute() {
        logger.debug("==========================开始创建项目==========================");
        config = new ConfigBuilder(
                this.globalConfigBuilder.build(),
                this.dataSourceConfigBuilder.build(),
                this.strategyConfigBuilder.build()
        );
        templateEngine.init(config).generate();
        logger.debug("==========================创建项目完成==========================");
    }

}
