#!/bin/bash

# See instructions in https://github.com/tieto-pc/aws-devops-intro-demo/blob/master/README.md#local-codebuild

if [ $# -ne 0 ]
then
    echo "Usage: ./run-local-codebuild-build-and-test.sh"
    exit 1
fi


./codebuild_build.sh -i aws/codebuild/ubuntu:18 -a local-artifacts -b buildspec_build_and_test.yml

