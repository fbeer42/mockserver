#!/usr/bin/env bash

# java 1.6 build
export MAVEN_OPTS='-XX:MaxPermSize=1024m -Xmx1024m'
export JAVA_OPTS='-XX:MaxPermSize=1024m -Xmx1024m'
if [ $? -eq 0 ]; then
    export JAVA_HOME=`/usr/libexec/java_home -v 1.6`
    mvn release:clean -Drelease.arguments="-DnonReleaseBuild=false -Dmaven.test.skip=true -DskipTests=true" && \
    mvn release:prepare -Drelease.arguments="-DnonReleaseBuild=false -Dmaven.test.skip=true -DskipTests=true" && \
    mvn release:perform -Drelease.arguments="-DnonReleaseBuild=false -Dmaven.test.skip=true -DskipTests=true"
fi