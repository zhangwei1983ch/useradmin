FROM maven:3-jdk-8
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ONBUILD ADD . /usr/src/app
ONBUILD RUN mvn package
ONBUILD ADD target/spring-session-ui-0.0.1-SNAPSHOT.war /user/src/app
ENTRYPOINT [ "java", "-jar", "spring-session-ui-0.0.1-SNAPSHOT.war"]

