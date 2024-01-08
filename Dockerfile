FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN  ./mvnw package -DskipTests
#./mvnw package

FROM openjdk:17
WORKDIR financialmanagement
COPY --from=build target/*.jar financialmanagement.jar
ENTRYPOINT ["java", "-jar", "financialmanagement.jar"]