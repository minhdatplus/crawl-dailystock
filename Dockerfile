FROM maven:3.6.3-jdk-11-slim AS build
COPY . /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:8-jdk-alpine
EXPOSE 8080/tcp
RUN  apk update && apk upgrade && apk add netcat-openbsd
WORKDIR historical-market-service
COPY --from=build /app/target/historical-market-service.jar historical-market-service.jar
RUN chmod -R 777 historical-market-service.jar
ADD run.sh .
RUN chmod +x run.sh
CMD ./run.sh