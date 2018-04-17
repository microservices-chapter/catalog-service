FROM java:8-jre

ADD ./build/libs/catalog-service-1.0-SNAPSHOT.jar /service/
CMD ["java", "-Xmx200m", "-DlogPath=/logs", "-jar", "/service/catalog-service-1.0-SNAPSHOT.jar"]

EXPOSE 8081
