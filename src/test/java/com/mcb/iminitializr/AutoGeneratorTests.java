package com.mcb.iminitializr;

import org.junit.jupiter.api.Test;

class AutoGeneratorTests {

    @Test
    void contextLoads() {
        AutoGenerator.create()
                .globalConfig(builder -> {
                    builder.groupId("com.mcb")
                            .artifactId("test")
                            .outputDir("/Users/mochuangbiao/Files");
                })
                .execute();
    }

}
