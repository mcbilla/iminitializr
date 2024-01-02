package com.mcb.iminitializr.engine;

import com.mcb.iminitializr.config.ConfigBuilder;
import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.constant.Constant;
import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.support.PathFactory;
import com.mcb.iminitializr.support.impl.PathFactoryImpl;
import com.mcb.iminitializr.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplateEngine implements PathFactory<PathEnum> {
    private ConfigBuilder configBuilder;

    private PathFactory<PathEnum> pathFactory;

    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        this.pathFactory = new PathFactoryImpl(configBuilder.getGlobalConfig());
        return doInit(configBuilder);
    }

    public void generate() {
        ConfigBuilder config = this.getConfigBuilder();
        // 创建整体项目
        generateProject(config);
        // TODO 创建其他内容
    }

    /**
     * 创建整体项目
     *
     * @param config
     */
    private void generateProject(ConfigBuilder config) {
        GlobalConfig globalConfig = config.getGlobalConfig();
        // 获取文件名，大写开头驼峰式
        String applicationName = FileUtils.separatorToCamel(globalConfig.getArtifactId(), Constant.DASH, Constant.APPLICATION_NAME + Constant.JAVA_SUFFIX);
        // 获取包名
        String packageVal = getPackage(PathEnum.pkg);
        // 获取包路径
        String packagePath = getPath(PathEnum.pkg);
        // 完整文件名，包路径 + 文件名
        String fileName = packagePath + File.separatorChar + applicationName;
        File file = new File(fileName);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("applicationName", applicationName);
        objectMap.put("packageName", packageVal);
        this.outputFile(file, objectMap, getTemplatePath(Constant.APPLICATION_TEMPLATE));
    }

    /**
     * @param file
     * @param objectMap
     * @param templatePath
     */
    protected void outputFile(File file, Map<String, Object> objectMap, String templatePath) {
        try {
            // 全局判断【默认】
            boolean exist = file.exists();
            if (!exist) {
                File parentFile = file.getParentFile();
                FileUtils.forceMkdir(parentFile);
            }
            doWrite(objectMap, templatePath, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPath(PathEnum pathEnum) {
        return this.pathFactory.getPath(pathEnum);
    }

    @Override
    public String getPackage(PathEnum pathEnum) {
        return this.pathFactory.getPackage(pathEnum);
    }

    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }

    public abstract AbstractTemplateEngine doInit(ConfigBuilder configBuilder);

    /**
     * 生成文件
     *
     * @param objectMap    用于填充模板内占位符的数据集合
     * @param templatePath 模板路径（模板名+后缀）
     * @param file         生成的文件
     * @throws Exception
     */
    protected abstract void doWrite(Map<String, Object> objectMap, String templatePath, File file) throws Exception;

    public abstract String getTemplatePath(String temlateName);
}
