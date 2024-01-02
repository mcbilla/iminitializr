package com.mcb.iminitializr.engine;

import com.mcb.iminitializr.config.ConfigBuilder;
import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.constant.Constant;
import com.mcb.iminitializr.utils.FileUtils;
import org.apache.tomcat.util.buf.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplateEngine {
    private ConfigBuilder configBuilder;

    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return doInit(configBuilder);
    }

    public void generate() {
        ConfigBuilder config = this.getConfigBuilder();
        // 创建整体项目
        generateProject(config);
        // TODO 创建其他内容
    }

    /**
     * 创建启动文件
     *
     * @param config
     */
    private void generateProject(ConfigBuilder config) {
        GlobalConfig globalConfig = config.getGlobalConfig();
        String groupId = globalConfig.getGroupId();
        String artifactId = globalConfig.getArtifactId();
        // 获取包名，逗号隔开
        String packageName = groupId + Constant.DOT + artifactId.replace(Constant.DASH, Constant.DOT);
        // 把包名转换成路径，文件分隔符隔开
        String packagePath = packageName.replace(Constant.DOT, File.separator);
        // 获取文件名，大写开头驼峰式
        StringBuffer applicationName = new StringBuffer();
        String[] split = artifactId.split(Constant.DASH);
        for (String s : split) {
            applicationName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        applicationName.append(Constant.APPLICATION_NAME);
        // 完整文件名，根路径 + 包路径 + 文件名
        String fileName = StringUtils.join(Arrays.asList(globalConfig.getOutputDir(), packagePath, applicationName.append(Constant.JAVA_SUFFIX).toString()),
                File.separatorChar);
        File file = new File(fileName);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("applicationName", applicationName);
        objectMap.put("packageName", packageName);
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
