FROM java:8
VOLUME ["/tmp"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
RUN mkdir -p /webform/logs
RUN mkdir -p /webform/uploaded/images
RUN mkdir -p /webform/uploaded/tls
RUN mkdir -p /webform/uploaded/tmp
RUN chmod -R 777 /webform
ADD ./webform-*.jar /webform/app.jar
ADD ./cert.pfx /webform/uploaded/tls/cert.pfx
EXPOSE 8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/webform/app.jar"]