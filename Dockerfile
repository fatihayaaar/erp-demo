FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/erp-demo-1.0.jar erp-demo.jar

ENV PROFILES=native
ENV SERVER_PORT=8080

ENV DB_URL=localhost:5432/erp-demo
ENV DB_USERNAME=root
ENV DB_PASSWORD=root
ENV DB_URL_TEST=localhost:5432/erp-demo-test
ENV DB_USERNAME_TEST=test
ENV DB_PASSWORD_TEST=test

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/erp-demo.jar"]