# 简介

<div>
<p>The name "Property Hive" appears to be a combination of two words: "property" and "hive". "Property" refers to real estate or land and "hive" can refer to a place where bees live and work together. In the context of a property management system, the name "Property Hive" may suggest a system that helps manage and organize a large number of properties in a centralized and efficient manner, similar to how bees work together in a hive.</p>
<p align="right">——Github Copilot X</p>
</div>

# 特色

1. gradle
2. SpringSecurity(JWT)
3. SpringData JPA
4. SpringBoot Email (Microsoft 365)
5. Redis
6. RabitMQ
7. TencentSMS
8. Docker
9. Github Actions

# 预览

http://20.239.163.26:8081

# 构建项目

构建镜像

```shell
docker build -t property_hive .
```
查看镜像
```shell
docker images ls
```
运行镜像
```shell
docker run \
  -e DB_HOST=localhost \
  -e DB_PORT=3306 \
  -e DB_NAME=property_hive \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=123456 \
  property_hive
```
