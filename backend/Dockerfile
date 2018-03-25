FROM java:8

WORKDIR /

COPY target/shortly.jar shortly.jar
COPY config.yml config.yml

EXPOSE 8080

CMD java -jar shortly.jar server config.yml
