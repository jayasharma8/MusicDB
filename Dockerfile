From java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=musicdb
ENV MYSQL_PORT=3306
ENV MYSQL_HOST=localhost
ADD ./target/MusicDB-Service-0.0.1-SNAPSHOT.jar /usr/src/MusicDB-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/usr/src/MusicDB-Service-0.0.1-SNAPSHOT.jar"]