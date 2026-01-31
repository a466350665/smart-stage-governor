# Smart Stage Governor

基于 [Smart Stage](https://github.com/a466350665/smart-stage) 技术底座的应用聚合工程，包含一个聚合启动的应用与两个可独立部署的示例服务。
主要演示：MyBatis-Plus 数据访问、OpenFeign 跨模块调用、Nacos 服务发现、基础 CRUD 与分页能力。

## 模块说明

- `smart-stage-governor-boot`：聚合启动模块，依赖 `smart-stage-sample1-service` 与 `smart-stage-sample2-service`，便于单体方式快速验证（默认端口 8080）。
- `smart-stage-sample1`
  - `smart-stage-sample1-api`：对外 API/DTO 定义（`DemoApi`，供 Feign 调用）。
  - `smart-stage-sample1-service`：示例 CRUD 服务（Demo 表、MyBatis-Plus）。
  - `smart-stage-sample1-boot`：sample1 独立启动模块（默认端口 8081），支持 Swagger UI。
- `smart-stage-sample2`
  - `smart-stage-sample2-service`：示例消费方，通过 Feign 调用 sample1。
  - `smart-stage-sample2-boot`：sample2 独立启动模块（默认端口 8082）。

## 技术栈

- Java 17
- Spring Boot / Spring Cloud OpenFeign
- Smart-Stage：`smart-stage-core`、`smart-stage-starter`、`smart-stage-starter-mybatisplus`
- MyBatis-Plus + MySQL
- Nacos（仅在分布式启动时需要）
- Springdoc OpenAPI（sample1）

## 快速开始

### 1) 数据库初始化

使用 `init.sql` 初始化示例表 `t_demo`：

```bash
mysql -u root -p smart-stage-sample1 < init.sql
```

根据实际环境修改数据源配置：
- `smart-stage-governor-boot/src/main/resources/application.yaml`
- `smart-stage-sample1/smart-stage-sample1-boot/src/main/resources/application.yaml`

### 2) 单体方式（聚合启动）

无需 Nacos，直接启动聚合模块：

```bash
mvn -pl smart-stage-governor-boot -am spring-boot:run
```

### 3) 分布式方式（sample1 + sample2）

启动 Nacos（默认 `127.0.0.1:8848`），然后分别启动：

```bash
mvn -pl smart-stage-sample1/smart-stage-sample1-boot -am spring-boot:run
mvn -pl smart-stage-sample2/smart-stage-sample2-boot -am spring-boot:run
```

## 接口示例

sample1（CRUD + 分页）：
- `GET /sample1/demo/page`
- `GET /sample1/demo/list`
- `POST /sample1/demo`
- `PUT /sample1/demo`
- `DELETE /sample1/demo/{id}`
- `POST /sample1/demo/api/page`（Feign 调用入口）

sample2（跨模块调用 sample1）：
- `GET /sample2/consumer/page`

Swagger UI（sample1）：
- `http://localhost:8081/swagger-ui/index.html`

## 配置说明

- 应用基础配置：各 boot 模块 `application.yaml`。
- 国际化资源：各 boot 模块 `messages_zh_CN.properties` 与 `messages_en_US.properties`。
- 插件级配置：`smart-stage-sample1/smart-stage-sample1-service/src/main/resources/plugin/sample1`、
    `smart-stage-sample2/smart-stage-sample2-service/src/main/resources/plugin/sample2`。
  - [Smart Stage](https://github.com/a466350665/smart-stage) 会自动加载 `plugin/*` 下的配置与资源，适合“按模块/插件”组织业务配置。
  - 约定目录结构：
    ```
    plugin/
    └── ${symbol}/
        ├── application.yaml
        ├── application-dev.yaml
        ├── messages_zh_CN.properties
        └── messages_en_US.properties
    ```

  - 加载规则：
    - `plugin/*/application.yml|yaml|properties` 会被自动加载，支持 profile（如 `application-dev.yaml`）
    - `plugin/*/messages*.properties` 作为国际化资源加载

## Docker

各 boot 模块内置 Dockerfile：
- `smart-stage-governor-boot/Dockerfile`
- `smart-stage-sample1/smart-stage-sample1-boot/Dockerfile`
- `smart-stage-sample2/smart-stage-sample2-boot/Dockerfile`

构建镜像前请确保 `target` 下的 jar 名称与 Dockerfile 中保持一致（`smart-stage-governor.jar`、`smart-stage-sample1.jar`、`smart-stage-sample2.jar`）。
