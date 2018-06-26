FROM java:8

WORKDIR /

COPY build/libs/crisco.jar crisco.jar
COPY config.yml config.yml

EXPOSE 8080

CMD java -jar crisco.jar server config.yml
