#!/bin/bash
CONTAINER_NAME="tomcat-container"

echo "Pulling Tomcat docker image"
docker pull tomcat:latest

docker run -d -p 32772:8080 --name "$CONTAINER_NAME" tomcat