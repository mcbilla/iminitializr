package com.mcb.iminitializr;

import org.junit.jupiter.api.Test;

class AutoGeneratorTests {

    @Test
    void contextLoads() {
        AutoGenerator.create()
                .globalConfig(builder -> {
                    builder.groupId("com.mcb")
                            .artifactId("my-test2")
                            .outputDir("/Users/mochuangbiao/Files")
                            .author("mcb");
                })
                .dataSourceConfigBuilder(builder -> {
                    builder.url("jdbc:mysql://localhost:3306/java_study?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")
                            .username("root")
                            .password("root");
                })
                .strategyConfigBuilder(builder -> {
                    builder.addInclude("t_user,t_manager") // 指定包含的表名，多个表之间逗号隔开，不调用该方法默认为所有表生成代码
                            .addTablePrefix("t_") // 过滤表前缀，即t_user变成user
                            .entityBuilder().enableFileOverride() // 实体文件覆盖
                            .mapperBuilder().enableFileOverride() // Mapper文件覆盖
                            .serviceBuilder().enableFileOverride() // Service文件覆盖
                            .controllerBuilder().enableFileOverride().enableRestStyle(); // Controller文件覆盖+开启RestController
                })
                .execute();
    }

}
