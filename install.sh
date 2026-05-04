#!/bin/bash
TOMCAT_CONTAINER_NAME="tomcat-container"
DB_IMAGE="mcr.microsoft.com/mssql/server"
DB_IMAGE_WITH_VERSION="mcr.microsoft.com/mssql/server:2019-latest"
MSSQL_CONTAINER_NAME="mssql-boardgames"
DB_PASSWORD="yrgoP4ssword"

if ! docker images | grep -q "$DB_IMAGE"; then
echo "Pulling SQL Server docker image"
docker pull "$DB_IMAGE_WITH_VERSION"
fi

docker run --name "$MSSQL_CONTAINER_NAME" -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=$DB_PASSWORD" -e "MSSQL_PID=Express" -p 1433:1433 -d "$DB_IMAGE_WITH_VERSION"

if ! docker images | grep -q "tomcat"; then
echo "Pulling Tomcat docker image"
docker pull tomcat:latest
fi

docker run -d -p 32772:8080 --name "$TOMCAT_CONTAINER_NAME" tomcat

#TODO: Init DB tables etc?