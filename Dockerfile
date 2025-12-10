FROM eclipse-temurin:17-jdk

COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar" ]