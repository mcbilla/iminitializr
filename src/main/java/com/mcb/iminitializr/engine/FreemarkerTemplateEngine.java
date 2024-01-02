package com.mcb.iminitializr.engine;

import com.mcb.iminitializr.config.ConfigBuilder;
import com.mcb.iminitializr.constant.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class FreemarkerTemplateEngine extends AbstractTemplateEngine{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Configuration configuration;

    @Override
    public AbstractTemplateEngine doInit(ConfigBuilder configBuilder) {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, Constant.TEMPLATE_ROOT_PATH);
        configuration.setDefaultEncoding(Constant.UTF8);
        return this;
    }

    @Override
    protected void doWrite(Map<String, Object> objectMap, String templatePath, File file) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        }
        logger.debug("模板:" + templatePath + ";  文件:" + file);
    }

    @Override
    public String getTemplatePath(String temlateName) {
        return temlateName + Constant.FREEMARKER_SUFFIX;
    }
}
