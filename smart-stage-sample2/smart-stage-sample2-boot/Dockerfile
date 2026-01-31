FROM eclipse-temurin:17-jre

COPY target/smart-stage-sample2.jar smart-stage-sample2.jar

CMD ["/bin/sh", "-c", "java ${BEFORE_ARGS} -Djava.security.egd=file:/dev/urandom -jar smart-stage-sample2.jar ${AFTER_ARGS}"]
