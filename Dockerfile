FROM java:8
FROM maven:3.2-jdk-7-onbuild
WORKDIR /lhc
ADD . /lhc
RUN mvn clean install
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]