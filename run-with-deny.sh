#!/bin/bash

java -Dspring.profiles.active=dev --illegal-access=deny -jar build/libs/java-simple-rest-demo-app-0.1.0.jar
