FROM maven:3-jdk-8
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn package
RUN mv target/spring-session-ui-0.0.1-SNAPSHOT.war /usr/src/spring-session-ui-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java", "-jar", "/usr/src/spring-session-ui-0.0.1-SNAPSHOT.war"]

