#!/usr/bin/bash -x

###############################################################################
# Remove previously generated code and create directories for generated files
###############################################################################

GEN_SRC_BASE=gen

rm -rf $GEN_SRC_BASE

mkdir -p ${GEN_SRC_BASE}/{client,server}/src/{java,test}

###############################################################################
# Configuration common to all generator invocations
###############################################################################

PROJECT_NAME=springboot3webflux

GROUP_ID=com.github.gordonforce.demo

COMMON_ARGS="--input-spec config/web-ping.yaml \
  --git-host github.com \
  --git-repo-id ${PROJECT_NAME} \
  --git-user-id gordonforce \
  --group-id ${GROUP_ID} \
  --artifact-version 0.0.1-SNAPSHOT"

PACKAGE_BASE=${GROUP_ID}.${PROJECT_NAME}

######################################################################
# Generate a Client using Java and Spring WebClient
######################################################################

GEN_CLIENT_SRC_JAVA_BASE=${GEN_SRC_BASE}/client/src/java

CLIENT_PACKAGE_BASE=${PACKAGE_BASE}.client

openapi-generator \
  generate \
    --generator-name java \
    ${COMMON_ARGS,//\'//} \
    --output ${GEN_CLIENT_SRC_JAVA_BASE}/server \
    --artifact-id ${PROJECT_NAME}-server \
    --api-package ${CLIENT_PACKAGE_BASE}.api \
    --model-package ${CLIENT_PACKAGE_BASE}.model \
    --invoker-package ${CLIENT_PACKAGE_BASE}.invoker \
    --config generators/java-webclient.yaml

######################################################################
# Generate a Client using Java and Spring WebClient
######################################################################

GEN_SERVER_SRC_SPRING_BASE=${GEN_SRC_BASE}/server/src/java

SERVER_PACKAGE_BASE=${PACKAGE_BASE}.server

openapi-generator \
  generate \
    --generator-name spring \
    ${COMMON_ARGS,//\'//} \
    --output ${GEN_SERVER_SRC_SPRING_BASE}/server \
    --artifact-id ${PROJECT_NAME}-server \
    --api-package ${SERVER_PACKAGE_BASE}.api \
    --model-package ${SERVER_PACKAGE_BASE}.model \
    --invoker-package ${SERVER_PACKAGE_BASE}.invoker \
    --config generators/spring-reactive.yaml
