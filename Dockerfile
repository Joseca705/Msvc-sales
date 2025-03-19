FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
EXPOSE 8060
ADD ./target/sales-0.0.1-SNAPSHOT.jar msvc-sales.jar

ENTRYPOINT ["java", "-jar", "msvc-sales.jar"]
