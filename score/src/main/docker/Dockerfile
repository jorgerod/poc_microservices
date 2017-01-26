FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ADD score-0.0.1-SNAPSHOT.jar score.jar

RUN sh -c 'touch /score.jar'

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /score.jar" ]
