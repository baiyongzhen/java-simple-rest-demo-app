# Java Simple Rest Demo CRM Application  <!-- omit in toc -->


# Table of Contents  <!-- omit in toc -->
- [Introduction](#introduction)
- [Instructions to Run the Application](#instructions-to-run-the-application)


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

Yes. There are only 3 customers in our demo CRM system. Try to live with that, dude.
