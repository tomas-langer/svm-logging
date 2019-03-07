#!/usr/bin/env bash
#
# Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Configuration of reflection, needed for custom classes that should be instantiated or access by reflection
GRAAL_OPTIONS="-H:ReflectionConfigurationFiles=./etc/graal/reflection-config.json"

# Configure all resources that should be available in runtime (except for META-INF/services - those are added
# by Helidon SVM Extension)
INCLUDE_RES="logging.properties"
GRAAL_OPTIONS="${GRAAL_OPTIONS} -H:IncludeResources=${INCLUDE_RES}"

# This should be "set in stone"
# GRAAL_OPTIONS="${GRAAL_OPTIONS} --delay-class-initialization-to-runtime=${DELAY_INIT}"

echo "Graal options: ${GRAAL_OPTIONS}"

${NATIVE_IMAGE} -jar target/init-static.jar ${GRAAL_OPTIONS}
# --delay-class-initialization-to-runtime=io.helidon.reproducer.svm.TestClass is required so that TestClass.LOGGER is initialized after logging.properties has been processed
${NATIVE_IMAGE} --delay-class-initialization-to-runtime=io.helidon.reproducer.svm.TestClass -jar target/init-main.jar ${GRAAL_OPTIONS}
