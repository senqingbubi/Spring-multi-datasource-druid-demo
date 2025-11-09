多数据源配置
此项目用spring原生多数据源配置

主数据源：mysql
副数据源：oracle 或 gauss 二选一(也可配置其他数据源)


激活 open profile：
   # 在 application.yml 中添加
   spring:
     profiles:
       active: open
在 application-open.yml 中配置：
    spring:
     datasource:
       use-db: oracle  # 或其他你想使用的数据库类型

当 open profile 被激活时，配置加载顺序为：
application.yml 先加载
application-open.yml 后加载，其中的配置会覆盖前面的配置
这样 ${spring.datasource.use-db:oracle} 就能读取到 application-open.yml 中设置的值。
