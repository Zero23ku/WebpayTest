FROM openjdk:8
ARG JAR_FILE

WORKDIR /opt/app

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_DEBUG= \
    JAVA_DEBUG_PORT=8787 \
    AB_OFF=true \
    JAVA_APP_JAR=app.jar \
    JAVA_APP_DIR=/opt/app \
    JAVA_OPTIONS="-Djava.security.egd=file:/dev/./urandom -Duser.stimezone=America/Santiago -Dhttp.nonProxyHosts=localhost|127.0.0.1|10.*.*.*|192.*.*.* -Dcom.sun.management.jmxremote.rmi.port=9090 -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=9090 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.local.only=false -Djava.rmi.server.hostname=localhost"

ADD target/${JAR_FILE} app.jar

VOLUME /tmp

EXPOSE 9000 9001 8787

ENTRYPOINT java $JAVA_OPTIONS -jar app.jar
