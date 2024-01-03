package com.mcb.iminitializr.config;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;

public class DataSourceConfig {
    /**
     * 驱动连接的URL
     */
    private String url;

    /**
     * 数据库连接用户名
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    private DataSourceConfig() {
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder implements IConfigBuilder<DataSourceConfig> {
        private final DataSourceConfig dataSourceConfig;

        public Builder() {
            this.dataSourceConfig = new DataSourceConfig();
        }

        public Builder url(String url) {
            this.dataSourceConfig.url = url;
            return this;
        }

        public Builder username(String username) {
            this.dataSourceConfig.username = username;
            return this;
        }

        public Builder password(String password) {
            this.dataSourceConfig.password = password;
            return this;
        }

        public DataSourceConfig build() {
            return this.dataSourceConfig;
        }
    }
}
