FROM java:8
WORKDIR /home/ubuntu/app/lhc
CMD ["./mvnw", "clean", "install"]
WORKDIR /home/ubuntu/app/lhc/target
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]