FROM maven:3.6.3-jdk-11-slim as builder
RUN groupadd --system --gid 1000 legolas
RUN useradd --system --gid legolas --uid 1000 --shell /bin/bash --create-home legolas
USER legolas
WORKDIR /home/legolas
RUN mkdir /home/legolas/app
COPY src /home/legolas/app/src
COPY pom.xml /home/legolas/app
RUN mvn -f /home/legolas/app clean package -DskipTests

FROM openjdk:11.0.4-jre-slim as prod
ARG MONGO_HOST=${MONGO_HOST}
ARG MONGO_DB=${MONGO_DB}
ARG MONGO_PORT=${MONGO_PORT}
ARG MONGO_USERNAME=${MONGO_USERNAME}
ARG MONGO_PASSWORD=${MONGO_PASSWORD}
RUN groupadd --system --gid 1000 gandalf
RUN useradd --system --gid gandalf --uid 1000 --shell /bin/bash --create-home gandalf
USER gandalf
WORKDIR /home/gandalf
COPY --from=builder /home/legolas/app/target/laboratorio-*.jar /home/gandalf/app/laboratorio.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-D spring.data.mongodb.uri=mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOST}:${MONGO_PORT}/${MONGO_DB}", "-jar", "/home/gandalf/app/laboratorio.jar" ]