#!/bin/bash

mvn clean package

docker cp target/boardgame-club-administration.war tomcat-container:/usr/local/tomcat/webapps/

docker restart tomcat-container
