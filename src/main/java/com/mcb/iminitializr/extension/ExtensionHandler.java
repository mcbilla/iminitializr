package com.mcb.iminitializr.extension;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展的思路很简单：拿到模板，渲染数据，生成文件并输出到目录
 *
 * 所以扩展接口的关键四要素如下：
 * 1、模板名，例如application.java.ftl，模板文件必须放在src/resources/templates下面
 * 2、待渲染的数据集合，使用Map<String, Object>类型
 * 3、输出文件名，不需要带后缀，例如HelloController
 * 4、输出文件路径，基于根目录的相对路径，例如src/main/java/com/mcb/hello，为了方便获取路径继承了PathFactory，可以通过getPath来获取路径
 */
public interface ExtensionHandler {
    /**
     * 是否启用插件，默认返回true
     * @return true表示启用，false表示禁用
     */
    default boolean enable() {
        return true;
    }

    /**
     * 插件名，默认为【生成插件全路径 + 生成插件文件名】
     * @return
     */
    default String getName() {
        return getOutputFilePath() + File.separator + getOutputFileName();
    }

    /**
     * 插件模板名，插件模板必须放在 src/main/resources/templates 下面
     * 插件模板名默认不需要加模版引擎后缀，例如插件模板是 test.java.ftl，插件模板名只需要返回 test.java
     * @return
     */
    @NotNull
    String getTemplateName();

    /**
     * 用于渲染模板的数据
     * @return
     */
    @NotNull
    default Map<String, Object> getObjectMap() {
        return new HashMap<>();
    }

    /**
     * 生成的插件文件名
     * @return
     */
    @NotNull
    String getOutputFileName();

    /**
     * 生成的插件全路径
     * @return
     */
    @NotNull
    String getOutputFilePath();
}
