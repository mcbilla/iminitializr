package com.mcb.iminitializr;

import org.junit.jupiter.api.Test;

class AutoGeneratorTests {

    @Test
    void contextLoads() {
        AutoGenerator.create()
                .globalConfig(builder -> {
                    builder.groupId("com.mcb")
                            .artifactId("test")
                            .outputDir("/Users/mochuangbiao/Files")
                            .author("mcb");
                })
                .dataSourceConfigBuilder(builder -> {
                    builder.url("jdbc:mysql://localhost:3306/java_study?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")
                            .username("root")
                            .password("root");
                })
                .strategyConfigBuilder(builder -> {
                    builder.addInclude("user");
                })
                .execute();
    }

}
