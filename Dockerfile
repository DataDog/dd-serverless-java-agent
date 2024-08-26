FROM mcr.microsoft.com/java/maven:8-zulu-debian10

WORKDIR /app

COPY . /app

RUN mvn clean package
