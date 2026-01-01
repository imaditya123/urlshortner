# # ---- Build stage ----
# FROM gradle:8.5-jdk17 AS build
# WORKDIR /app
# COPY . .
# RUN gradle clean bootJar

# # ---- Runtime stage ----
# FROM eclipse-temurin:17-jre
# WORKDIR /app
# COPY --from=build /app/build/libs/*.jar app.jar

# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]


# ---- Build stage ----
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar
RUN ls -la /app && ls -la /app/build || true
RUN find /app -name "*.jar"

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/**/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
