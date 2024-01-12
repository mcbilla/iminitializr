<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="30">
    <Properties>
        <property name="LOG_HOME">./logs</property>
    </Properties>

    <Appenders>
        <!--*********************控制台日志***********************-->
        <Console name="consoleAppender" target="SYSTEM_OUT">
            <!--设置日志格式及颜色-->
            <PatternLayout
                    pattern="%style{%d{ISO8601}}{bright,green} %highlight{%-5level} [%style{%t}{bright,blue}] %style{%C{}}{bright,yellow}: %msg%n%style{%throwable}{red}"
                    disableAnsi="false" noConsoleNoAnsi="false" />
        </Console>

        <!--*********************文件日志***********************-->
        <!--debug级别日志-->
        <RollingFile name="debugFileAppender"
                     fileName="${r'${LOG_HOME}/debug.log'}"
                     filePattern="${r'${LOG_HOME}/$${date:yyyy-MM}/debug-%d{yyyy-MM-dd}-%i.log.gz'}">
            <Filters>
                <!--过滤掉info及更高级别日志-->
                <ThresholdFilter level="info" onMatch="DENY" onMismatch="NEUTRAL" />
            </Filters>
            <!--设置日志格式-->
            <PatternLayout
                    pattern="[%d{yyyy-MM-dd HH:mm:ss:SSS}] %X{THREAD_ID}[%-5level] [%t] %class{36} %L %M - %msg%xEx%n" />
            <Policies>
                <!-- 设置日志文件切分参数 -->
                <!--<OnStartupTriggeringPolicy/>-->
                <!--设置日志基础文件大小，超过该大小就触发日志文件滚动更新-->
                <SizeBasedTriggeringPolicy size="100 MB" />
                <!--设置日志文件滚动更新的时间，依赖于文件命名filePattern的设置-->
                <TimeBasedTriggeringPolicy />
            </Policies>
            <!--设置日志的文件个数上限，不设置默认为7个，超过大小后会被覆盖；依赖于filePattern中的%i-->
            <DefaultRolloverStrategy max="100" />
        </RollingFile>

        <!--info级别日志-->
        <RollingFile name="infoFileAppender"
                     fileName="${r'${LOG_HOME}/info.log'}"
                     filePattern="${r'${LOG_HOME}/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log.gz'}">
            <Filters>
                <!--过滤掉warn及更高级别日志-->
                <ThresholdFilter level="warn" onMatch="DENY" onMismatch="NEUTRAL" />
            </Filters>
            <!--设置日志格式-->
            <PatternLayout
                    pattern="[%d{yyyy-MM-dd HH:mm:ss:SSS}] %X{THREAD_ID}[%-5level] [%t] %class{36} %L %M - %msg%xEx%n" />
            <Policies>
                <!-- 设置日志文件切分参数 -->
                <!--<OnStartupTriggeringPolicy/>-->
                <!--设置日志基础文件大小，超过该大小就触发日志文件滚动更新-->
                <SizeBasedTriggeringPolicy size="100 MB" />
                <!--设置日志文件滚动更新的时间，依赖于文件命名filePattern的设置-->
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>

        <!--warn级别日志-->
        <RollingFile name="warnFileAppender"
                     fileName="${r'${LOG_HOME}/warn.log'}"
                     filePattern="${r'${LOG_HOME}/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log.gz'}">
            <Filters>
                <!--过滤掉error及更高级别日志-->
                <ThresholdFilter level="error" onMatch="DENY" onMismatch="NEUTRAL" />
            </Filters>
            <!--设置日志格式-->
            <PatternLayout
                    pattern="[%d{yyyy-MM-dd HH:mm:ss:SSS}] %X{THREAD_ID}[%-5level] [%t] %class{36} %L %M - %msg%xEx%n" />
            <Policies>
                <!-- 设置日志文件切分参数 -->
                <!--<OnStartupTriggeringPolicy/>-->
                <!--设置日志基础文件大小，超过该大小就触发日志文件滚动更新-->
                <SizeBasedTriggeringPolicy size="100 MB" />
                <!--设置日志文件滚动更新的时间，依赖于文件命名filePattern的设置-->
                <TimeBasedTriggeringPolicy />
            </Policies>
            <!--设置日志的文件个数上限，不设置默认为7个，超过大小后会被覆盖；依赖于filePattern中的%i-->
            <DefaultRolloverStrategy max="100" />
        </RollingFile>

        <!--error及更高级别日志-->
        <RollingFile name="errorFileAppender"
                     fileName="${r'${LOG_HOME}/error.log'}"
                     filePattern="${r'${LOG_HOME}/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log.gz'}">
            <!--设置日志格式-->
            <PatternLayout
                    pattern="[%d{yyyy-MM-dd HH:mm:ss:SSS}] %X{THREAD_ID}[%-5level] [%t] %class{36} %L %M - %msg%xEx%n" />
            <Policies>
                <!-- 设置日志文件切分参数 -->
                <!--<OnStartupTriggeringPolicy/>-->
                <!--设置日志基础文件大小，超过该大小就触发日志文件滚动更新-->
                <SizeBasedTriggeringPolicy size="100 MB" />
                <!--设置日志文件滚动更新的时间，依赖于文件命名filePattern的设置-->
                <TimeBasedTriggeringPolicy />
            </Policies>
            <!--设置日志的文件个数上限，不设置默认为7个，超过大小后会被覆盖；依赖于filePattern中的%i-->
            <DefaultRolloverStrategy max="100" />
        </RollingFile>

    </Appenders>

    <Loggers>
        <!-- 根日志设置 -->
        <Root level="info">
            <AppenderRef ref="consoleAppender" level="debug" />
            <AppenderRef ref="debugFileAppender" level="debug" />
            <AppenderRef ref="infoFileAppender" level="info" />
            <AppenderRef ref="warnFileAppender" level="warn" />
            <AppenderRef ref="errorFileAppender" level="error" />
        </Root>

        <!-- log4j2 自带过滤日志-->
        <logger name="springfox" level="warn" />
        <logger name="org.springframework" level="warn" />
        <logger name="org.apache" level="warn" />
        <logger name="org.hibernate" level="warn" />
        <Logger name="com.mybatis" level="warn" />
        <Logger name="org.hibernate" level="warn" />
        <Logger name="com.zaxxer.hikari" level="info" />
        <Logger name="org.quartz" level="info" />
        <Logger name="druid.sql.Statement" level="warn" />
    </Loggers>

</Configuration>