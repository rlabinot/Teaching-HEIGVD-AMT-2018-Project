#!/bin/bash
cd swagger/spring-server
mvn clean
mvn install
cp target/swagger-spring-1.0.0.jar ../../docker/images/WP2/spring/swagger-spring-1.0.0.jar
cd ../../docker/topologies/WP2/deploy/
docker-compose up --build