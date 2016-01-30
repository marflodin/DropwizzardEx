FROM java:8

WORKDIR /data

ADD /target/hello-world-dw-ws-0.0.1-SNAPSHOT.jar /data/hello-world-dw-ws-0.0.1-SNAPSHOT.jar

ADD example.keystore /data/example.keystore

ADD hello-world.yml /data/hello-world.yml

CMD java -jar hello-world-dw-ws-0.0.1-SNAPSHOT.jar server hello-world.yml

EXPOSE 8080