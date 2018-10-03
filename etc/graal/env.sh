#!/usr/bin/env bash

export JAVA_HOME=$HOME/bin/jdk-jvmci-8/Contents/Home
export PATH=${JAVA_HOME}/bin:${PATH}

export NATIVE_IMAGE="$HOME/github/mx/mx --java-home=$HOME/bin/jdk-jvmci-8/Contents/Home -p $HOME/github/graal/substratevm native-image"

