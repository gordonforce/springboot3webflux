#!/usr/bin/bash -x

GENERATED_SRC_BASE=gen

GENERATED_SRC_JAVA_BASE=${GENERATED_SRC_BASE}/client/src/java

rm -rf $GENERATED_SRC_BASE

mkdir -p ${GENERATED_SRC_BASE}/client/src/{java,test}

PROJECT_NAME=springboot3webflux

GROUP_ID=com.github.gordonforce.demo

COMMON_ARGS="--input-spec config/web-ping.yaml \
  --git-host github.com \
  --git-repo-id ${PROJECT_NAME} \
  --git-user-id gordonforce \
  --group-id ${GROUP_ID} \
  --artifact-version 0.0.1-SNAPSHOT"

PACKAGE_BASE=${GROUP_ID}.${PROJECT_NAME}

CLIENT_PACKAGE_BASE=${PACKAGE_BASE}.client

openapi-generator \
  generate \
  --generator-name java \
  ${COMMON_ARGS,//\'//,//([,=])\s+/$1/} \
  --output ${GENERATED_SRC_JAVA_BASE}/client \
  --artifact-id ${PROJECT_NAME}-client \
  --api-package ${CLIENT_PACKAGE_BASE}.api \
  --model-package ${CLIENT_PACKAGE_BASE}.model \
  --invoker-package ${CLIENT_PACKAGE_BASE}.invoker \
  --config bin/java-client-first.yaml
