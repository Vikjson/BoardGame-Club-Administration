# ===== BUILD STAGE =====
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ===== RUNTIME STAGE =====
FROM tomcat:11.0.21-jdk21

COPY --from=build \
/app/target/boardgame-club-administration.war \
/usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
