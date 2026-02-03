# ===== BUILD STAGE =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# copy service-b pom
COPY service-b/pom.xml ./pom.xml

# install employee-common inside container
COPY com.example/target/employee-common-1.0-SNAPSHOT.jar /tmp/employee-common.jar
RUN mvn install:install-file \
    -Dfile=/tmp/employee-common.jar \
    -DgroupId=org.example \
    -DartifactId=employee-common \
    -Dversion=1.0-SNAPSHOT \
    -Dpackaging=jar

# copy service-b source
COPY service-b/src ./src

# build service-a
RUN mvn clean package -DskipTests

# ===== RUNTIME STAGE =====
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
