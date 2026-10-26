# 苍穹外卖 · sky-take-out

基于 Spring Boot 的外卖业务后端学习项目，包含管理端、用户端及支付通知接口，并按公共组件、数据模型和服务入口拆分为 Maven 模块。

本仓库没有随附管理端前端和微信小程序前端工程。

## 功能范围

| 模块 | 代码中的功能 |
| --- | --- |
| 员工与基础管理 | 员工登录/状态/资料、分类管理、文件上传 |
| 菜品与套餐 | 新增、修改、删除、分页、详情及启售/停售 |
| 店铺 | 营业状态设置与查询 |
| 用户端 | 微信登录相关接口、地址簿、分类/菜品/套餐浏览 |
| 购物车 | 添加、减少、列表和清空 |
| 用户订单 | 提交、支付接口、历史、详情、取消、再来一单和催单 |
| 管理端订单 | 条件查询、统计、接单、拒单、取消、派送和完成 |
| 经营分析 | 工作台数据、营业额/用户/订单统计、销量排名、Excel 报表导出 |
| 通知 | WebSocket 与微信支付通知相关代码 |

真实微信登录、支付和回调需要自行准备账号、商户及相关配置并完成联调；存在接口代码不等于已开通真实支付能力。

## 模块与技术

```text
sky-take-out/
├─ sky-common/    公共常量、工具、异常和配置属性
├─ sky-pojo/      DTO、VO 与实体
├─ sky-server/    启动类、接口、业务、Mapper、配置和报表模板
└─ pom.xml       聚合构建
```

技术栈包括 Spring Boot **2.7.3**、MyBatis、PageHelper、Druid、MySQL、Redis、Spring Cache、JWT、Knife4j、WebSocket、阿里云 OSS、Apache POI 和微信支付 SDK。

请使用兼容当前 Spring Boot 2.7.x 与依赖的 JDK；项目父 POM 未显式声明 Java 编译版本，构建前应确认 `JAVA_HOME`。

## 运行准备

1. 安装 JDK、Maven，准备 MySQL 和 Redis。
2. 获取与当前实体及 Mapper 匹配的数据库结构和初始数据。当前仓库未包含 SQL 初始化脚本。
3. 检查配置：
   - [application.yml](sky-server/src/main/resources/application.yml)：主配置，默认启用 `dev`。
   - [application-dev.yml](sky-server/src/main/resources/application-dev.yml)：开发环境参数。
4. 按实际环境填写 `sky.datasource`、`sky.redis`、`sky.alioss`、`sky.wechat` 与 JWT 相关设置。
5. 微信、支付证书和云存储凭据使用自己的测试环境信息，不要把真实密钥提交到仓库。

## 构建与启动

在仓库根目录运行：

```shell
git clone https://github.com/Trappedqueens/sky-take-out.git
cd sky-take-out
mvn clean package -DskipTests
java -jar sky-server/target/sky-server-1.0-SNAPSHOT.jar
```

默认端口为 **8080**。也可以在 IDE 中运行 `com.sky.SkyApplication`。

项目采用多模块结构，请从根目录构建公共模块，避免只进入 `sky-server` 后缺少本地模块依赖。

## 接口分组

| 路径前缀 | 作用 |
| --- | --- |
| `/admin/employee` | 员工登录与管理 |
| `/admin/category` | 分类 |
| `/admin/dish`、`/admin/setmeal` | 菜品与套餐 |
| `/admin/shop` | 营业状态 |
| `/admin/order` | 订单处理 |
| `/admin/report`、`/admin/workspace` | 经营统计与工作台 |
| `/user/user` | 用户登录 |
| `/user/addressBook` | 地址簿 |
| `/user/shoppingCart` | 购物车 |
| `/user/order` | 下单与用户订单 |
| `/notify/paySuccess` | 支付通知入口 |

管理端 JWT 默认从 `token` 请求头读取，用户端默认从 `authentication` 请求头读取，可在配置中调整。详细请求体和参数见 [controller](sky-server/src/main/java/com/sky/controller)。

若文档组件已启用并且服务正常启动，可查看 `http://localhost:8080/doc.html`；以当前 Knife4j 配置及拦截规则为准。

## 限制与验证

- 数据库、Redis、OSS 和微信环境需自行准备，不是克隆仓库后即可免配置运行。
- Excel 导出使用仓库内的报表模板。
- 没有配套前端；需要自行对接管理端和小程序页面。
- 本次仅新增 README，未修改业务代码，也未执行构建、数据库操作、短信或支付联调。
