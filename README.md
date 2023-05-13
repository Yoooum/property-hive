构建镜像
```shell
docker build -t property_hive .
```
查看镜像
```shell
docker images
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
