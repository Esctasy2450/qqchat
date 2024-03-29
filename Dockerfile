FROM --platform=linux/arm64 mdsol/java8-jdk

RUN mkdir -p /dc-park

WORKDIR /dc-park

ARG JAR_FILE=target/dc-park-1.0.0.jar

COPY ${JAR_FILE} app.jar

EXPOSE 7003

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD java $JAVA_OPTS -jar app.jar --eureka.client.enabled=true