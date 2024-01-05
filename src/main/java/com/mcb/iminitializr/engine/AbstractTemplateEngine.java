package com.mcb.iminitializr.engine;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.mcb.iminitializr.config.ConfigBuilder;
import com.mcb.iminitializr.config.DataSourceConfig;
import com.mcb.iminitializr.config.GlobalConfig;
import com.mcb.iminitializr.constant.Constant;
import com.mcb.iminitializr.constant.PathEnum;
import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.support.ExtensionFactory;
import com.mcb.iminitializr.support.RuntimeFactory;
import com.mcb.iminitializr.support.impl.ExtensionFactoryImpl;
import com.mcb.iminitializr.support.impl.RuntimeFactoryImpl;
import com.mcb.iminitializr.utils.FileUtils;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractTemplateEngine implements RuntimeFactory, ExtensionFactory<ExtensionHandler> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTemplateEngine.class);

    private ConfigBuilder configBuilder;

    private RuntimeFactory runtimeFactory;

    private ExtensionFactory<ExtensionHandler> extensionFactory;

    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        this.runtimeFactory = preparePathFactory();
        this.extensionFactory = prepareExtensionFactory();
        return doInit(configBuilder);
    }

    private RuntimeFactory preparePathFactory() {
        return new RuntimeFactoryImpl(this.configBuilder.getGlobalConfig());
    }

    private ExtensionFactory<ExtensionHandler> prepareExtensionFactory() {
        return new ExtensionFactoryImpl(this.runtimeFactory);
    }

    public void generate() {
        ConfigBuilder config = this.getConfigBuilder();
        // 创建整体项目
        generateProject(config);
        // 创建基本MVC结构
        generateMVC(config);
        // 创建扩展
        generateExtension(config);
    }

    /**
     * 创建项目整体架构，包括application、package-info、test、yml、pom、.gitignore
     *
     * @param config
     */
    private void generateProject(ConfigBuilder config) {
        GlobalConfig globalConfig = config.getGlobalConfig();
        // 根目录路径
        String rootPath = getAbsolutePath(PathEnum.root);
        // java包路径
        String packagePath = getAbsolutePath(PathEnum.pkg);
        // java资源路径
        String resourcePath = getAbsolutePath(PathEnum.resource);
        // 测试包路径
        String testPackagePath = getAbsolutePath(PathEnum.test_pkg);

        // 1、创建application
        // 获取application名，大写开头驼峰式
        String applicationName = FileUtils.separatorToCamel(globalConfig.getArtifactId(), Constant.DASH, Constant.APPLICATION_NAME);
        this.outputFile(createFile(packagePath, applicationName, Constant.JAVA_SUFFIX),
                Constant.APPLICATION_TEMPLATE,
                builder -> builder
                        .put("applicationName", applicationName)
                        .put("packageName", getPackage(PathEnum.pkg))
                        .getAll());

        // 2、创建package-info
        this.outputFile(createFile(packagePath, Constant.PACKAGE_INFO_TEMPLATE, null),
                Constant.PACKAGE_INFO_TEMPLATE,
                builder -> builder
                        .put("packageName", getPackage(PathEnum.pkg))
                        .getAll());

        // 3、创建test
        String testName = FileUtils.separatorToCamel(globalConfig.getArtifactId(), Constant.DASH, Constant.TEST_NAME);
        this.outputFile(createFile(testPackagePath, testName, Constant.JAVA_SUFFIX),
                Constant.TEST_TEMPLATE,
                builder -> builder
                        .put("testName", testName)
                        .put("packageName", getPackage(PathEnum.test_pkg))
                        .getAll());

        // 4、创建yml
        this.outputFile(createFile(resourcePath, Constant.YML_TEMPLATE, null),
                Constant.YML_TEMPLATE,
                null);

        // 5、创建pom
        this.outputFile(createFile(rootPath, Constant.POM_TEMPLATE, null),
                Constant.POM_TEMPLATE,
                builder -> builder
                        .put("groupId", globalConfig.getGroupId())
                        .put("artifactId", globalConfig.getArtifactId())
                        .put("version", globalConfig.getVersion())
                        .put("name", globalConfig.getName())
                        .put("description", globalConfig.getDescription())
                        .getAll()
        );

        // 6、创建.gitignore
        this.outputFile(createFile(rootPath, Constant.GITIGNORE_TEMPLATE, null),
                Constant.GITIGNORE_TEMPLATE,
                null);
    }

    /**
     * 基于mybatis-plus-generator，创建MVC结构
     *
     * @param config
     */
    private void generateMVC(ConfigBuilder config) {
        DataSourceConfig dataSourceConfig = config.getDataSourceConfig();
        @NotNull
        String url = dataSourceConfig.getUrl();
        @NotNull
        String username = dataSourceConfig.getUsername();
        @NotNull
        String password = dataSourceConfig.getPassword();
        Assert.noNullElements(new String[]{url, username, password}, "数据库连接信息不能为空");
        new AutoGenerator(new com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder(url, username, password).build())
                .global(new com.baomidou.mybatisplus.generator.config.GlobalConfig.Builder()
                        .author(config.getGlobalConfig().getAuthor()) // 设置作者
                        .enableSwagger() // 开启 swagger 模式
                        .outputDir(getAbsolutePath(PathEnum.pkg)) // 指定输出目录
                        .build())
                .packageInfo(new PackageConfig.Builder()
                        .parent(getPackage(PathEnum.pkg)) // 设置父包名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, getAbsolutePath(PathEnum.resource) + Constant.XML_PATH)) // 设置mapperXml生成路径
                        .build())
                .injection(new InjectionConfig.Builder()
                        .customFile(new CustomFile.Builder() // 自定义DTO
                                .fileName("DTO.java")
                                .filePath(getAbsolutePath(PathEnum.pkg))
                                .packageName(getPackage(PathEnum.pkg) + ".dto")
                                .enableFileOverride()
                                .templatePath("templates/dto.java.ftl")
                                .build())
                        .build())
                .strategy(config.getStrategyConfig())
                .execute(new com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine());
    }

    private void generateExtension(ConfigBuilder config) {
        // 提供一个扩展接口，用于增加用户自定义数据
        ((RuntimeFactoryImpl)this.runtimeFactory).putAllData(prepareDataMap());
        List<ExtensionHandler> extensions = this.extensionFactory.getExtensions();
        extensions.forEach(e -> {
            logger.debug("加载插件，实现类:" + e.getClass().getSimpleName() + "; 插件名:" + e.getName());
            this.outputFile(createFile(e.getOutputFilePath(), e.getOutputFileName(), Constant.JAVA_SUFFIX),
                    Constant.APPLICATION_TEMPLATE,
                    builder -> builder.putAll(e.getObjectMap()).getAll());
        });
    }

    /**
     * 扩展接口，用户自定义数据
     * @return
     */
    protected Map<String, Object> prepareDataMap() {
        return new HashMap<>();
    }

    /**
     * 创建java文件
     *
     * @param path   绝对路径
     * @param name   文件名
     * @param suffix 文件名后缀
     * @return
     */
    private File createFile(String path, String name, String suffix) {
        StringBuffer sb = new StringBuffer()
                .append(path)
                .append(File.separatorChar)
                .append(name)
                .append(suffix != null ? suffix : "");
        return new File(sb.toString());
    }

    /**
     * 使用模板引擎渲染并输出文件
     *
     * @param file
     * @param templateName
     * @param objectMapBuilder
     */
    protected void outputFile(File file, String templateName, Function<ObjectMapBuilder, Map<String, Object>> objectMapBuilder) {
        try {
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                FileUtils.forceMkdir(parentFile);
            }
            Map<String, Object> objectMap = objectMapBuilder != null ? objectMapBuilder.apply(new ObjectMapBuilder()) : null;
            doWrite(objectMap, doGetTemplatePath(templateName), file);
        } catch (IOException e) {
            throw new RuntimeException("文件写入异常", e);
        } catch (TemplateException e) {
            throw new RuntimeException("模板渲染异常", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAbsolutePath(PathEnum pathEnum) {
        return this.runtimeFactory.getAbsolutePath(pathEnum);
    }

    @Override
    public String getRelativePath(PathEnum pathEnum) {
        return this.runtimeFactory.getRelativePath(pathEnum);
    }

    @Override
    public String getPackage(PathEnum pathEnum) {
        return this.runtimeFactory.getPackage(pathEnum);
    }

    @Override
    public Object getData(String key) {
        return this.runtimeFactory.getData(key);
    }

    @Override
    public ExtensionHandler getExtension(String name) {
        return this.extensionFactory.getExtension(name);
    }

    @Override
    public List<ExtensionHandler> getExtensions() {
        return this.extensionFactory.getExtensions();
    }

    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }

    /**
     * 模板引擎初始化
     *
     * @param configBuilder
     * @return
     */
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

    /**
     * 获取带引擎后缀的模板名
     *
     * @param temlateName
     * @return
     */
    public abstract String doGetTemplatePath(String temlateName);

    /**
     * 作用是为了使用lambda表达式创建objectMap
     */
    class ObjectMapBuilder {
        private Map<String, Object> objectMap = new HashMap<>();

        public ObjectMapBuilder put(String key, Object obj) {
            objectMap.putIfAbsent(key, obj);
            return this;
        }

        public ObjectMapBuilder putAll(Map<String, Object> map) {
            objectMap.putAll(map);
            return this;
        }

        public Map<String, Object> getAll() {
            return this.objectMap;
        }
    }
}
