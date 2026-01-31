FROM eclipse-temurin:17-jre

COPY target/smart-stage-governor.jar smart-stage-governor.jar

CMD ["/bin/sh", "-c", "java ${BEFORE_ARGS} -Djava.security.egd=file:/dev/urandom -jar smart-stage-governor.jar ${AFTER_ARGS}"]
