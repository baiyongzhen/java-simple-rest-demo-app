#!/bin/bash

docker run -it -p5055:5055 --workdir="/opt/mylib" --entrypoint /bin/sh tieto-pc/java-devops-crm-demo:0.1
