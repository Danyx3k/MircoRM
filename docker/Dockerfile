# Build: docker build -f docker/Dockerfile --build-arg MODULE=microrm-api -t microrm-api ..
# MODULE = microrm-api | microrm-gateway

FROM eclipse-temurin:26-jdk-alpine AS build
WORKDIR /build

RUN apk add --no-cache bash

COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
COPY pom.xml .
COPY microrm-api/pom.xml microrm-api/pom.xml
COPY microrm-gateway/pom.xml microrm-gateway/pom.xml

RUN chmod +x mvnw \
	&& ./mvnw -pl microrm-api,microrm-gateway -am dependency:go-offline -B -q || true

COPY microrm-api/src microrm-api/src
COPY microrm-gateway/src microrm-gateway/src

ARG MODULE=microrm-api
RUN ./mvnw -pl "${MODULE}" -am package -DskipTests -B -q

FROM eclipse-temurin:26-jre-alpine
RUN apk add --no-cache curl

WORKDIR /app
ARG MODULE=microrm-api
COPY --from=build /build/${MODULE}/target/*-SNAPSHOT.jar /app/app.jar

ARG APP_PORT=8080
ENV APP_PORT=${APP_PORT}
EXPOSE ${APP_PORT}

HEALTHCHECK --interval=15s --timeout=5s --retries=5 --start-period=60s \
	CMD curl -fsS "http://127.0.0.1:${APP_PORT}/actuator/health" | grep -q '"status":"UP"' || exit 1

ENTRYPOINT ["sh", "-c", "exec java -jar /app/app.jar"]
