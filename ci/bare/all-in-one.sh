#!/bin/sh

################################################
#
#      All-in-one installation of Cubed
#
################################################
#
# This script is used to deploy Cubed as an
# all-in-one server from git. Meaning all
# services are on the same host.
#
# @author jdesive
################################################

system="$1"

# Update and install java git postgres screen and maven
sudo apt-get update -y && sudo apt-get install openjdk-8-jdk git maven postgresql screen -y

# Create cubed database
sudo -u postgres createdb cubed

# Clone Cubed
cd /tmp && sudo git clone https://git.sw4pspace.net/Sw4pSpace/Cubed.git

# NOTE: Could checkout a specific branch here based on a parameter?

#
# Build Process
#

cd Cubed

# Install main POM
mvn clean install -pl net.sw4pspace:cubed

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

# Copy service files
if [ -z ${system} ] || [ ${system} != "init.d" ]
then
    sudo cp ci/bare/systemd/cubed-webservices.service /etc/systemd/system/cubed-webservices.service
    sudo cp ci/bare/start-cubed-webservices.sh /opt/cubed-webservices/start.sh
    sudo chmod u+x /opt/cubed-webservices/start.sh
    sudo cp ci/bare/systemd/cubed-server.service /etc/systemd/system/cubed-server.service
    sudo cp ci/bare/start-cubed-server.sh /opt/cubed-server/start.sh
    sudo chmod u+x /opt/cubed-server/start.sh
fi

# Copy final jars
sudo mkdir /opt/cubed-server
sudo cp cubed-server/target/cubed-server.jar /opt/cubed-server/cubedserver.jar
sudo mkdir /opt/cubed-webservices
sudo cp cubed-webservices/target/cubed-webservices.jar /opt/cubed-webservices/cubed-webservices.jar

# Run both
sudo systemctl daemon-reload
sudo systemctl enable cubed-server.service
sudo systemctl start cubed-server.service
sudo systemctl enable cubed-webservices.service
sudo systemctl start cubed-webservices.service

# fin