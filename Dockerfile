FROM ubuntu:latest
LABEL authors="GeloX"

# Użycie oficjalnego obrazu Javy 17 jako bazowego
FROM openjdk:17-jdk-slim

# Ustawienie zmiennej środowiskowej dla katalogu aplikacji
ENV APP_HOME=/app

# Utworzenie katalogu aplikacji
WORKDIR $APP_HOME

# Kopiowanie pliku JAR aplikacji do kontenera
COPY target/projBack-0.0.1-SNAPSHOT.jar app.jar

# Ustawienia zmiennych środowiskowych dla Spring Boot
ENV SPRING_DATASOURCE_URL=jdbc:mysql://172.18.0.2:3306/fullstack
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SPRING_APPLICATION_NAME=projBack
ENV SPRING_MAIN_WEB_APPLICATION_TYPE=servlet
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQL8Dialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JACKSON_TIME_ZONE=UTC

# Eksponowanie portu aplikacji
EXPOSE 8080

# Uruchamianie aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]
