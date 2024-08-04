# open jdk 21 버전의 환경을 구성한다.
FROM openjdk:21-jdk

### gradle을 이용해 빌드를 실행한다
#CMD ["./gradlew", "clean", "build"]

#빌드 될때 jar 파일 위치 선언
ARG JAR_FILE=build/libs/*.jar

# jar 파일을 복사한다.
COPY ${JAR_FILE} app.jar

# jar 파일을 실행한다.
ENTRYPOINT ["java", "-jar", "-Dspring.config.import=optional:file:../../.env\[.properties\]","-D spring.profiles.active=dev", "/app.jar"]