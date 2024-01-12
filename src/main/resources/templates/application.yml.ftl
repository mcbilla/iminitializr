server:
  port: 8081

spring:
  application:
    name: ${applicationName}
  jackson:
    date-format: yyy-MM-dd HH:mm:ss
    time-zone: Asia/Shanghai
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${url}
    username: ${username}
    password: ${password}
    hikari:
      minimum-idle: 10
      maximum-pool-size: 20
      idle-timeout: 600000
      max-lifetime: 1800000
      connection-timeout: 60000
  # Springfox使用的路径匹配是基于AntPathMatcher的，而Spring Boot 2.6.X使用的是PathPatternMatcher，该配置是为了解决兼容问题
  mvc:
    pathmatch:
      matching-strategy: ANT_PATH_MATCHER

mybatis-plus:
  # 启动时是否检查MyBatis XML文件是否存在
  check-config-location: true
  # MyBatis原生配置
  configuration:
    # 字段名称下划线转驼峰命名
    map-underscore-to-camel-case: true
    # 配置控制台打印完整带参数SQL语句，生产可以关闭该配置
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 解决数据库为null的字段属性不返回问题
    call-setters-on-nulls: true
  global-config:
    db-config:
      # 全局默认主键类型
      id-type: ASSIGN_ID
      # 逻辑已删除值(默认为 1)
      logic-delete-value: 1
      # 逻辑未删除值(默认为 0)
      logic-not-delete-value: 0
  # mapper xml映射路径
  mapper-locations: classpath*:mapper/**/*Mapper.xml