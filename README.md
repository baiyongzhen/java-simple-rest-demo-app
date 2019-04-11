# Java Simple Rest Demo CRM Application  <!-- omit in toc -->


# Table of Contents  <!-- omit in toc -->
- [Introduction](#introduction)
- [Instructions to Run the Application](#instructions-to-run-the-application)
- [AWS CodePipeline and CodeBuild](#aws-codepipeline-and-codebuild)


# Introduction

This directory hosts a simple Java Rest application that simulates a CRM system. The application is used in various cloud infra demonstrations e.g. baked into a Docker container image or into a virtual machine image.


# Instructions to Run the Application

First build the application:

```bash
 ./clean-build.sh
```

Then start the application:

```bash
./run-with-deny.sh 
```

Then curl the application:

```bash
curl http://localhost:5055/customer/1
# => {"ret":"ok","customer":{"id":1,"email":"kari.karttinen@foo.com","firstName":"Kari","lastName":"Karttinen"}}
curl http://localhost:5055/customer/4
# => {"ret":"failed","msg":"Could not find customer with id 4"}
```


# AWS CodePipeline and CodeBuild

File [buildspec.yml](buildspec.yml) is the AWS CodeBuild build specification file. This demonstration is used in the [aws-devops-intro-demo](https://github.com/tieto-pc/aws-devops-intro-demo) project to demonstrate how to host the code in [AWS CodeCommit](https://aws.amazon.com/codecommit/), and how to create a build pipeline using [AWS CodePipeline](https://aws.amazon.com/codepipeline/) which delegates the actual build process for [AWS CodeBuild](https://aws.amazon.com/codebuild/) (which uses the buildspec.yml file as an instruction how to build the application jar).

See project [aws-devops-intro-demo](https://github.com/tieto-pc/aws-devops-intro-demo) for a more detailed description how the AWS devops tools are used with this project.

