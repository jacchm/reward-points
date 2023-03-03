FROM gradle:7.5.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon
RUN ls -l /home/gradle/src/build
RUN ls -l /home/gradle/src/build/libs

FROM openjdk:17.0.2-jdk
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar" ,"/app/spring-boot-application.jar"]
