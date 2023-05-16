FROM openjdk:17

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/ai-0.0.1-SNAPSHOT.jar ${JAR_PATH}/ai-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","./build/libs/ai-0.0.1-SNAPSHOT.jar"]