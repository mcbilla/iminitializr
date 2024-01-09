server:
  port: 8081

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${url}
    username: ${username}
    password: ${password}
  # Springfox使用的路径匹配是基于AntPathMatcher的，而Spring Boot 2.6.X使用的是PathPatternMatcher，该配置是为了解决兼容问题
  mvc:
    pathmatch:
      matching-strategy: ANT_PATH_MATCHER

mybatis-plus:
  configuration:
    # 配置控制台打印完整带参数SQL语句
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 解决数据库为null的字段属性不返回问题
    call-setters-on-nulls: true