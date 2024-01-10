# 脚手架工程

**iminitializr** 是一个轻松上手的 java 脚手架项目，只需要一行代码就可以生成增强版 MVC 结构的 Maven 项目。相比传统 MVC 结构，增加了统一入参打印、统一异常处理、统一返回处理等丰富特性。如果内置特性还不能满足你的需求，iminitializr 还支持插件化定制开发，你可以轻松创建满足你需求的定制化项目。

## 一、什么是脚手架？

简单来说，脚手架就是在界面或者公用接口上，传入必要的少量参数，就可以创建出一个应用开发框架。脚手架可以简化具有共性重复操作的简单工作，不再需要程序员还得一点点粘贴复制。下面是我们常用的脚手架。

### 1、项目脚手架

这类脚手架用于创建一个完整的项目，比较常用的是 **Spring Initializr** 和 **Aliyun Java Initializr**。

#### Spring Initializr

Spring Initializr 是所有 java 开发入门必备的脚手架。可以分为 start.spring.io 和 Initializr 两块内容

*  **start.spring.io**：本质上是一个 Web 应用，它可以通过 Web 界面、Spring Tool Suite、IntelliJ IDEA 等方式，构建出一个基本的 Spring Boot 项目结构。另外还可以下载 start.spring.io 源码进行本地部署。start.spring.io 本身并不创建内容，而是通过引入 Initializr 依赖，使用 Initializr 来创建内容。

> 链接：[https://start.spring.io](https://start.spring.io/)
>
> 源码：https://github.com/spring-io/start.spring.io

* **Initializr**：Initializr 是真正干活创建内容的地方。如果我们有定制化开发的需求，可以下载源码进行二次开发，也可以在自己的脚手架工程里面引入 initializr 依赖。

>  源码：https://github.com/spring-io/initializr

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.spring.initializr</groupId>
            <artifactId>initializr-bom</artifactId>
            <version>0.13.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### Aliyun Java Initializr

Aliyun Java Initializr 和 Spring Initializr 是同类的 Web 服务，是代码框架生成器，一键生成你的代码框架，有完善的工具链，免费的 IDEA 插件，方便直接在 IDE 中生成，同时也非常适合国内用户的网络环境。

> 链接：https://start.aliyun.com

### 2、基于数据库表的脚手架

这类脚手架是为数据表的表创建一套 MVC 结构，常用的有：

* **Mybatis Generator**：Mybatis 官方提供的代码生成器。
* **Mybatis-Plus-Generator**：Mybatis-Plus 内置代码生成器，相比起 Mybatis Generator 功能更强大，配置更简单。
* **PDManaer**：一款数据库建模工具，内置代码生成器。

在国内最常用的还是 Mybatis-Plus-Generator，这个工具确实非常好用，可以极大减少复制粘贴的工作量。

> 文档：https://baomidou.com/pages/981406/
>
> 源码：https://github.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator

## 二、为何要重复造轮子？

既然有这么多好用的工具，为何还要重复造轮子？主要是出于以下几个因素考虑：

* **可以创建完整项目的脚手架**。这里的完整是指包含完整 MVC 结构的 Maven 项目。Spring Initializr 可以创建一个包含各种依赖的 Maven 项目，Mybatis-Plus-Generator 可以基于数据库表创建 MVC 结构，而我需要的是两者功能的结合。

> Mybatis-Plus-Generator 除了可以基于数据库表创建 MVC 结构之外，其实也可以自定义 Custom 文件。但是这个功能目前还比较弱，用来创建一个完整的项目的话需要配置的内容太多了，不推荐使用。

* **简化创建流程**。Spring Initializr 确实功能很强大，但是配置实在有点繁琐。我们常用的依赖其实就那些，常用的项目结构、代码风格等其实也是固定的，完全可以把这些内容提炼出来。我更偏向于 Mybatis-Plus-Generator 那种一行代码创建项目的风格。
* **技术沉淀**。我们在日常开发的过程中，其实会积累很多通用的技术解决方案。可以把这些技术方案放到我们的脚手架里面，一方面可以用于规范项目结构，另一方面也可以当作技术知识的积累。
* **脚手架插件化**：我们的脚手架创建的项目不应该是一成不变的，而是应该随着技术的提升和需求的改变不断优化内容。新增的功能以可插拔的插件形式添加到脚手架里面，不会影响生成的项目框架，用户可以根据需求随时禁用、修改、启用插件。

基于以上因素，我收集了市面上大部分的脚手架项目进行分析，发现并没有能满足需求的脚手架，最后决定自己写一个 iminitializr。基于不重复造轮子的基本原则，iminitializr 内嵌 Mybatis-Plus-Generator，使用 Mybatis-Plus-Generator 来生成数据库表的 MVC 结构。iminitializr 集成了目前常用的项目依赖，整个项目默认配置项如下表所示：

| 配置项              | 版本        |
| ------------------- | ----------- |
| jdk                 | 1.8         |
| spring-boot-starter | 2.7.2       |
| mysql               | 8.0.13      |
| mybatis-plus        | 3.5.3.1     |
| lombok              | 1.8.26      |
| swagger             | 3.0.0       |
| hibernate-validator | 6.2.5.Final |

## 三、Quick Start

1、创建测试表 `t_user`

```sql
CREATE DATABASE mydb;

USE mydb;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '名字',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `lat` double(8,6) DEFAULT NULL COMMENT '纬度',
  `lng` double(8,6) DEFAULT NULL COMMENT '经度',
  `add_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

2、下载 iminitializr 项目。

```bash
git clone https://github.com/mcbilla/iminitializr.git
```

3、执行以下命令

```java
class AutoGeneratorTests {

    @Test
    void contextLoads() {
        AutoGenerator.create()
                .globalConfig(builder -> {
                    builder.groupId("com.mcb")
                            .artifactId("my-test3")
                            .outputDir("~/Files")
                            .author("mcbilla");
                })
                .dataSourceConfigBuilder(builder -> {
                    builder.url("jdbc:mysql://localhost:3306/java_study?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")
                            .username("root")
                            .password("root");
                })
                .strategyConfigBuilder(builder -> {
                    builder
                            // 指定包含的表名，多个表之间逗号隔开，不调用该方法默认为所有表生成代码
                            .addInclude("t_user")
                            // 过滤表前缀，即t_user变成user
                            .addTablePrefix("t_")
                            // 实体文件覆盖
                            .entityBuilder().enableFileOverride()
                            // Mapper文件覆盖、生成BaseResultMap、生成BaseColumnList
                            .mapperBuilder().enableFileOverride().enableBaseResultMap().enableBaseColumnList()
                            // Service文件覆盖
                            .serviceBuilder().enableFileOverride()
                            // Controller文件覆盖、开启RestController
                            .controllerBuilder().enableFileOverride().enableRestStyle();
                })
                .execute();
    }

}
```

4、进入 `~/Files` 目录，可以看到 `my-test` 项目已经被创建。项目结构如下所示：

```
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── mcb
    │   │           └── my
    │   │               └── test
    │   │                   ├── MyTestApplication.java
    │   │                   ├── config
    │   │                   │   └── WebConfig.java
    │   │                   ├── controller
    │   │                   │   └── UserController.java
    │   │                   ├── dto
    │   │                   │   └── UserDTO.java
    │   │                   ├── entity
    │   │                   │   └── User.java
    │   │                   ├── exception
    │   │                   │   └── BusnessException.java
    │   │                   ├── filter
    │   │                   │   └── WapperRequestFilter.java
    │   │                   ├── global
    │   │                   │   ├── GlobalExceptionHandler.java
    │   │                   │   ├── GlobalResponseBodyAdvice.java
    │   │                   │   ├── Result.java
    │   │                   │   └── ResultEnum.java
    │   │                   ├── interceptor
    │   │                   │   └── LogInterceptor.java
    │   │                   ├── mapper
    │   │                   │   └── UserMapper.java
    │   │                   ├── package-info.java
    │   │                   └── service
    │   │                       ├── IUserService.java
    │   │                       └── impl
    │   │                           └── UserServiceImpl.java
    │   └── resources
    │       ├── application.yml
    │       └── mapper
    │           └── UserMapper.xml
    └── test
        └── java
            └── com
                └── mcb
                    └── my
                        └── test
                            └── test
                                └── MyTestApplicationTests.java
```

使用 IDE 打开，项目初始化完成后，执行 `MyTestApplication`，访问 http://localhost:8081/user/hello ，可以看到下面响应

```
{
  "code": 200,
  "message": "成功",
  "data": "count: 1"
}
```

这样项目已经搭建完成了，就这么简单！

## 四、自定义插件

如果你觉得目前的功能还不能满足你的需求，iminitializr 支持通过插件的形式添加新的功能。插件接口是 `ExtensionHandler`。

```java
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
```

添加插件的步骤也很简单。第一步，创建你自己的 template 模板文件（目前只支持 Freemarker 模板），放在 `src/main/resources/templates` 目录下面。例如下面模板文件：

```java
package ${packageName};

public class ${className} {

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
```

第二步，实现 `ExtensionHandler`。为了让大家更方便拿到项目路径、包名等信息，推荐同时实现 `RuntimeFactoryAware` 接口，这里可以通过 `RuntimeFactory` 拿到运行时的一些信息。下面是一个实现例子

```java
public class MyExtension implements ExtensionHandler, RuntimeFactoryAware {
    private RuntimeFactory runtimeFactory;

    @Override
    public void setPathFactory(RuntimeFactory factory) {
        this.runtimeFactory = factory;
    }


    @Override
    public @NotNull String getTemplateName() {
        return "myextension.java";
    }

    @Override
    public @NotNull Map<String, Object> getObjectMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("packageName", runtimeFactory.getPackage(PathEnum.pkg) + ".myextension");
        objectMap.put("className", getOutputFileName());
        return objectMap;
    }

    @Override
    public @NotNull String getOutputFileName() {
        return "Myextension";
    }

    @Override
    public @NotNull String getOutputFilePath() {
        return this.runtimeFactory.getRelativePath(PathEnum.pkg) + "/myextension";
    }

    /**
     * 返回false可以禁用插件，默认情况下返回true
     * @return
     */
    @Override
    public boolean enable() {
        return true;
    }
}
```

查看 `my-test` 项目发现 `/my-test/src/main/java/com/mcb/my/test/myextension.java` 文件已经被创建，内容如下。

```java
package com.mcb.my.test.myextension;

public class Myextension {

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
```

如果想禁用插件的话，只需要在 `enable()` 方法里面返回 false。自定义插件就这么的简单！

# 感谢

你的星星是我前进的动力，感谢大家的支持～