#!/bin/bash

# NOTE:
# - You have to create aws-scripts directory in the root of this project and download the codebuild_build.sh file there.
# - The codebuild_build.sh is needed by script run-local-codebuild.sh (local CodeBuild tool).
# - I'm not including the aws script in this repo just in case that no-one complains that I have proprietary components in our repo. You can download the script that should be in this directory from AWS:
# - See: https://aws.amazon.com/blogs/devops/announcing-local-build-support-for-aws-codebuild/
# - Download the script like: wget https://raw.githubusercontent.com/aws/aws-codebuild-docker-images/master/local_builds/codebuild_build.sh

aws-scripts/codebuild_build.sh -i aws/codebuild/java:openjdk-8 -a local-artifacts -s .
