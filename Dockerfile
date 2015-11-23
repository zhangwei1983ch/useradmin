FROM maven:3.2-jdk-7-onbuild
#CMD wget http://download.oracle.com/otn/utilities_drivers/jdbc/121010/ojdbc7.jar
#CMD mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true
CMD java -jar spring-session-ui-0.0.1-SNAPSHOT.war