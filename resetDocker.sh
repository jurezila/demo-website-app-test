#!/usr/bin/env bash

echo
echo "This script will reset your whole docker project environment. Are you really sure about that? Press Control-C to cancel!"
echo

read -n1 -r -p "Press space to continue..." key
echo
echo

docker stop $(docker ps -a -q)
docker-compose pull
docker-compose rm -v -f
docker-compose build --no-cache

rm -f ./docker/mgnl_author/author.jar
rm -rf ./docker/nginx/apidoc/*

# Reset Magnolia Stuff

rm -rf ./magnolia/demo-website-webapp/overlays/*


echo
echo
echo "Now execute the following command:"
echo
echo "docker-compose up"
echo

