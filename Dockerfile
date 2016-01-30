FROM java:8-jre

COPY hello-world.yml /opt/dropwizard/
COPY target/hello-world-dw-ws-0.0.1-SNAPSHOT.jar /opt/dropwizard/

EXPOSE 9000
EXPOSE 9001

WORKDIR /opt/dropwizard

CMD ["java", "-jar", "-Done-jar.silent=true", "hello-world-dw-ws-0.0.1-SNAPSHOT.jar", "server", "hello-world.yml"]