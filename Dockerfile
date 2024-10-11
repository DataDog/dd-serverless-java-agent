FROM azul/zulu-openjdk:8

RUN apt-get -y update && apt-get -y install maven

WORKDIR /app

COPY . /app

RUN mvn clean package
