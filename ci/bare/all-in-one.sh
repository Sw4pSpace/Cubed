#!/bin/sh

################################################
#
#      All-in-one installation of Cubed
#
################################################
#
# This script is used to deploy Cubed as an
# all-in-one server. Meaning all services are
# on the same host.
#
# @author jdesive
################################################

# Update and install java git postgres screen and maven
sudo apt-get update -y && sudo apt-get install openjdk-8-jdk git maven postgresql screen -y

# Create cubed database
sudo -u postgres createdb cubed

# Clone Cubed
cd /tmp && sudo git clone https://github.com/Sw4pSpace/Cubed.git

# NOTE: Could check a specific branch here based on a parameter?

#
# Build Process
#

cd Cubed

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
sudo mkdir /opt/cubed && sudo cp target/cubedserver.jar /opt/cubed/cubedserver.jar
sudo mkdir /opt/cubed-webservices && sudo cp cubed-webservices/target/cubed-webservices.jar /opt/cubed-webservices/cubed-webservices.jar

# Run both
screen -S cubed-server -dm java -jar /opt/cubed/cubed.jar                                   # 25565
screen -S cubed-webservices -dm java -jar /opt/cubed-webservices/cubed-webservices.jar      # 8080

# fin