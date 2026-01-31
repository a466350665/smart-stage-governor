FROM eclipse-temurin:17-jre

COPY target/smart-stage-sample1.jar smart-stage-sample1.jar

CMD ["/bin/sh", "-c", "java ${BEFORE_ARGS} -Djava.security.egd=file:/dev/urandom -jar smart-stage-sample1.jar ${AFTER_ARGS}"]