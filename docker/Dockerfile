FROM maven:3.8.5-openjdk-17

WORKDIR /home/app

COPY pom.xml ./
COPY src ./src

RUN mvn -f ./pom.xml clean package

EXPOSE $SPRING_LOCAL_PORT

CMD ["java","-jar","/home/app/target/library_api.jar"]