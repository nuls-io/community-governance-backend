#!/bin/bash

rootdir=$PWD
CONF_PATH=$rootdir/app
nohup java -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -Xms1024m -Xmx1024m -Xss228k -Dspring.config.location=application-prod.yml -Dspring.profiles.active=prod -jar $CONF_PATH/community-governance-backend-1.0-SNAPSHOT.jar >starting.log 2>&1 &
