FROM openjdk:25-ea-4-jdk-oraclelinux9
WORKDIR /app
COPY /target /target

ENV SPRING_APPLICATION_PRODUCTDATAPATH '/data/products.json'
ENV SPRING_APPLICATION_ORDERDATAPATH '/data/orders.json'
ENV SPRING_APPLICATION_USERDATAPATH '/data/users.json'
ENV SPRING_APPLICATION_CARTDATAPATH '/data/carts.json'

EXPOSE 8080
CMD ["java","-jar","/target/mini1.jar"]