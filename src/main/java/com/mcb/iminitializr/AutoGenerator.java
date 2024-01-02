package com.mcb.iminitializr;

import com.mcb.iminitializr.config.ConfigBuilder;
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

    private AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();

    public AutoGenerator() {
        this.globalConfigBuilder = new GlobalConfig.Builder();
    }

    public static AutoGenerator create() {
        return new AutoGenerator();
    }

    public AutoGenerator globalConfig(Consumer<GlobalConfig.Builder> consumer) {
        consumer.accept(this.globalConfigBuilder);
        return this;
    }

    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        config = new ConfigBuilder(this.globalConfigBuilder.build());
        templateEngine.init(config).generate();
        logger.debug("==========================文件生成完成！！！==========================");
    }

}
