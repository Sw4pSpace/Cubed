#!/bin/sh

################################################
#
#      All-in-one installation of Cubed
#
################################################
#
# This script is used to deploy Cubed as an
# all-in-one server from local. Meaning all
# services are on the same host.
#
# @author jdesive
################################################

# This script assumes the required packages are already installed
cd ../../

# Install main POM
mvn clean install -pl com.sw4pspace:cubed

# Install cubed-api
cd cubed-api && mvn clean install

# Install cubed-server
cd .. && cd cubed-server && mvn clean install

# Run liquibase
cd .. && cd cubed-db && mvn clean install liquibase:update

# Install cubed-webservices
cd .. && cd cubed-webservices && mvn clean install

# return to /tmp/cubed
cd ..

# Copy final jars
cp cubed-webservices/target/cubed-webservices.jar target/cubed-webservices.jar