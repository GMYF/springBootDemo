# Docker image for springboot file run
#VERSION 0.0.1
# Author : gmyf
# docker镜像使用
FROM java:8
MAINTAINER mgyf<18366271217@163.com>
# VOLUME 指定了临时文件目录为/tmp
# 其效果是在主机 /var/lb/docker 目录下创建一个临时文件，并连接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD branch1-1.0-SNAPSHOT.jar app.jar
# 运行jar包
RUN bash -C 'touch /app.jar'
ENTRYPOINT ["java","-Dlsjava.security.egd=file:/dev/./urandom","-jar","/app.jar"]