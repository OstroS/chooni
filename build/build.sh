#!/bin/bash

TOMCAT_DIR=/home/kostrows/proj/apache-tomcat-7.0.33/webapps
AKIBA_DIR=/home/kostrows/proj/chooni/akiba

AKIBA_FRONTEND_WAR=$AKIBA_DIR/akiba-frontend/target/akiba-frontend.war
AKIBA_BACKEND_WAR=$AKIBA_DIR/akiba-backend/target/akiba-backend.war

cd /
cd $AKIBA_DIR
mvn clean install -DskipTests=true

cd
cp $AKIBA_BACKEND_WAR $TOMCAT_DIR/akiba-backend.war
cp $AKIBA_FRONTEND_WAR $TOMCAT_DIR/akiba-frontend.war

tail -f $TOMCAT_DIR/../logs/catalina.out
