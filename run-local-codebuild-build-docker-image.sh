#!/bin/bash

# See instructions in https://github.com/tieto-pc/aws-devops-intro-demo/blob/master/README.md#local-codebuild


if [ $# -ne 1 ]
then
    echo "Usage: ./run-local-codebuild-build-docker image.sh <AWS-PROFILE>"
    exit 1
fi

MY_AWS_PROFILE=$1

AWS_PROFILE=$MY_AWS_PROFILE ./codebuild_build.sh -c -i aws/codebuild/ubuntu:18 -a local-artifacts -b buildspec_build_docker_image.yml -e buildspec_build_docker_image_env_variables.txt

