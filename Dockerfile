FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=productdb
ENV MONGO_URL=mongodb://localhost:27017/productdb
ADD ./target/C2S4-0.0.1-SNAPSHOT.jar /usr/lib/C2S4-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","C2S4-0.0.1-SNAPSHOT.jar"]