#!/bin/bash
CONTAINER_NAME="tomcat-container"
WAR_FILE="boardgame-club-administration.war"

mvn clean package

if ! docker ps | grep -q "$CONTAINER_NAME"; then
    docker start "$CONTAINER_NAME"
fi

docker cp target/"$WAR_FILE" "$CONTAINER_NAME":/usr/local/tomcat/webapps/
docker restart "$CONTAINER_NAME"
