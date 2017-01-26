#!/usr/bin/env bash
#user
cd user && mvn clean package docker:build && cd ..

#score
cd score && mvn clean package docker:build && cd ..

#eureka
cd eurekaserver && mvn clean package docker:build && cd ..

#zuul
cd zuul && mvn clean package docker:build && cd ..

echo "**********************************************"
echo "SANITAS Environment Images are build succesfully!"
echo "**********************************************"
exit 0

