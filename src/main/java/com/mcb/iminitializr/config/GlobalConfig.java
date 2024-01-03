package com.mcb.iminitializr.config;

public class GlobalConfig {
    /**
     * 生成文件的输出目录【 windows:D://  linux or mac:/tmp 】
     */
    private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";

    private String groupId;

    private String artifactId;

    private String version;

    private String name;

    private String description;

    private String author;

    private GlobalConfig() {
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public static final class Builder implements IConfigBuilder<GlobalConfig> {
        private final GlobalConfig globalConfig;

        public Builder() {
            this.globalConfig = new GlobalConfig();
        }

        public Builder outputDir(String outputDir) {
            this.globalConfig.outputDir = outputDir;
            return this;
        }

        public Builder groupId(String groupId) {
            this.globalConfig.groupId = groupId;
            return this;
        }

        public Builder artifactId(String artifactId) {
            this.globalConfig.artifactId = artifactId;
            return this;
        }

        public Builder version(String version) {
            this.globalConfig.version = version;
            return this;
        }

        public Builder name(String name) {
            this.globalConfig.name = name;
            return this;
        }

        public Builder description(String description) {
            this.globalConfig.description = description;
            return this;
        }

        public Builder author(String author) {
            this.globalConfig.author = author;
            return this;
        }

        @Override
        public GlobalConfig build() {
            return this.globalConfig;
        }
    }
}
