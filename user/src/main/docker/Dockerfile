FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ADD user-0.0.1-SNAPSHOT.jar user.jar

RUN sh -c 'touch /user.jar'

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user.jar" ]