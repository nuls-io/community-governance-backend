#!/bin/bash
kill -9 `ps -ef| grep community-governance-backend-1.0-SNAPSHOT.jar |grep java | awk '{print $2}'`
