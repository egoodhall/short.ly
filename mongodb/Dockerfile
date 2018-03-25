FROM mongo:latest

RUN  mkdir -p /var/lib/mongodb && \
     touch /var/lib/mongodb/.keep && \
     chown -R mongodb:mongodb /var/lib/mongodb

ADD mongodb.conf /etc/mongodb.conf

VOLUME [ "/var/lib/mongodb" ]

EXPOSE 27017

USER mongodb
WORKDIR /var/lib/mongodb

ENTRYPOINT ["/usr/bin/mongod", "--config", "/etc/mongodb.conf"]
CMD ["--quiet"]