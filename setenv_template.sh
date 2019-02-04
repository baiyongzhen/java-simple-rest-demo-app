#!/bin/bash

# NOTE: Use OpenJDK version 10 (version 11 had some issues with Gradle).
export JAVA_HOME=
echo "JAVA_HOME="$JAVA_HOME
export GRADLE_HOME=
echo "GRADLE_HOME="$GRADLE_HOME
export M2_HOME=
echo "M2_HOME="$M2_HOME
export GROOVY_HOME=
echo "GROOVY_HOME="$GROOVY_HOME
export ANT_HOME=
echo "ANT_HOME="$ANT_HOME
export PATH=$JAVA_HOME/bin:$GRADLE_HOME/bin:$M2_HOME/bin:$GROOVY_HOME/bin:$ANT_HOME/bin:$PATH
echo "PATH="$PATH

