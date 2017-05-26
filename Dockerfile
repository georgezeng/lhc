FROM java:8
FROM maven:3.5.0
WORKDIR /lhc
ADD . /lhc
RUN mvn clean install
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]