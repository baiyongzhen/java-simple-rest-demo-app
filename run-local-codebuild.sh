#!/bin/bash

# See instructions in https://github.com/tieto-pc/aws-devops-intro-demo/blob/master/README.md#local-codebuild

./codebuild_build.sh -i aws/codebuild/ubuntu:18 -a local-artifacts -b buildspec.yml

