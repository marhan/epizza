#!/usr/bin/env bash

set -e

echo
echo "#############################################"
echo "# building 'gradle-plugins'                 #"
echo "#############################################"
./gradle-plugins/gradlew -p gradle-plugins build pTML

echo
echo "#############################################"
echo "# building 'shared'                         #"
echo "#############################################"
./shared/gradlew -p shared build pTML

echo
echo "#############################################"
echo "# building 'config-server'                  #"
echo "#############################################"
./config-server/gradlew -p config-server build
docker build --no-cache -t epizza/config-server:latest config-server

echo
echo "#############################################"
echo "# building 'order'                          #"
echo "#############################################"
./order/gradlew -p order build buildDockerImage

echo
echo "#############################################"
echo "# building 'bakery'                         #"
echo "#############################################"
./bakery/gradlew -p bakery build buildDockerImage

echo
echo "#############################################"
echo "# building 'delivery'                       #"
echo "#############################################"
./delivery/gradlew -p delivery build buildDockerImage

echo
echo "#############################################"
echo "# building 'order-ui'                       #"
echo "#############################################"
./order-ui/gradlew -p order-ui build
docker build --no-cache -t epizza/order-ui:latest order-ui
