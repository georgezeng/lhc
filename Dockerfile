FROM java:8
RUN git pull
RUN ./mvnw clean install
WORKDIR ./target
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]