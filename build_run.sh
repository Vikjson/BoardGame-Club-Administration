#!/bin/bash
TOMCAT_CONTAINER_NAME="tomcat-container"
MSSQL_CONTAINER_NAME="mssql-boardgames"
WAR_FILE="boardgame-club-administration.war"

mvn clean package

if ! docker ps | grep -q "$MSSQL_CONTAINER_NAME"; then
    docker start "$MSSQL_CONTAINER_NAME"
fi


if ! docker ps | grep -q "$TOMCAT_CONTAINER_NAME"; then
    docker start "$TOMCAT_CONTAINER_NAME"
fi

docker cp target/"$WAR_FILE" "$TOMCAT_CONTAINER_NAME":/usr/local/tomcat/webapps/
docker restart "$TOMCAT_CONTAINER_NAME"

cd frontend/
if ! curl http://localhost:5173; then
  echo "Running nmp run dev..."
  npm run dev
fi
