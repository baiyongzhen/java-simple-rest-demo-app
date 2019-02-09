#!/bin/bash

# NOTE: Uses application-dev.properties and logback-env-dev.groovy
# See "DEV" in every log line.
java -Dspring.profiles.active=dev -jar ../build/libs/java-simple-rest-demo-app-0.1.0.jar

