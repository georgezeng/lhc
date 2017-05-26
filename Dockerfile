FROM java:8
WORKDIR /lhc
ADD ./target /lhc
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]