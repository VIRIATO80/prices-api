FROM openjdk:18-ea-jdk-buster as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:18-ea-jdk-buster
LABEL maintainer="javi.lindo@gmail.com"
RUN mkdir /ebi
COPY --from=builder dependencies/ ./
RUN true
COPY --from=builder snapshot-dependencies/ ./
RUN true
COPY --from=builder spring-boot-loader/ ./
RUN true
COPY --from=builder application/ ./
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=70.0", "org.springframework.boot.loader.JarLauncher"]