#!/bin/bash

set -e

clear

cat utils/titles/title_build.txt

# shellcheck disable=SC2116
cr=$(echo $'\n> ')
cr=${cr%.}

# shellcheck disable=SC2162
read -p "\nDeseja criar Aplicativo[S/N]?: $cr" CONTINUE

# shellcheck disable=SC1009
if [ "$CONTINUE" = "s" ]
then
    cd ..

    printf "\n\nCleaning ...\n"
    ./gradlew clean

    printf "\n\nCleaning build cache ...\n"
    ./gradlew cleanBuildCache

    printf "\n\nCreate APK ...\n"
    ./gradlew assembleDebug

    printf "\n\nCopping...\n"
    cp -Rf "app/build/outputs/apk/debug" "/c/Users/${USER}/Desktop/Dev/"
    printf "\n\nSUCCESS\n"
else
  printf "\n\nOPERCAO CANCELADA"
fi






