FROM java:8
WORKDIR /lhc
ADD . /app
RUN ./mvnw clean install
CMD ["cd", "target"]
CMD ["java", "-DLOG_PATH=/home/ubuntu/logs/lhc", "-Dspring.profiles.active=uat", "-jar", "lhc.web.jar"]