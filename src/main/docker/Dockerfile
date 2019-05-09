FROM java:8
VOLUME ["/tmp"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
RUN mkdir -p /shaunzwork/webform/logs /shaunzwork/webform/system/images /shaunzwork/webform/system/tls /shaunzwork/webform/system/tmp /shaunzwork/webform/config
RUN chmod -R 777 /shaunzwork
ADD ./webform-*.jar /shaunzwork/webform/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/shaunzwork/webform/app.jar","--spring.config.location=file:/shaunzwork/webform/config/application.yml"]

#You can run this docker image via below command
#docker run -i -d -t --name="webform" --net="host" -v /shaunzwork/webform/system:/shaunzwork/webform/system -v /shaunzwork/webform/logs:/shaunzwork/webform/logs -v /shaunzwork/webform/config:/shaunzwork/webform/config shaunzwork/webform