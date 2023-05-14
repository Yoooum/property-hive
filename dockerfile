FROM azul/zulu-openjdk-alpine:17-jre

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=property_hive
ENV DB_USERNAME=root
ENV DB_PASSWORD=123456
EXPOSE 8080
COPY build/libs/*SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}","-Dspring.datasource.username=${DB_USERNAME}","-Dspring.datasource.password=${DB_PASSWORD}","-jar","/app.jar"]
