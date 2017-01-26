FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver.jar
RUN sh -c 'touch /eurekaserver.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eurekaserver.jar" ]