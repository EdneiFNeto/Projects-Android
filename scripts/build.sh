#!/bin/bash

set -e

clear

cat utils/titles/title_build.txt

# shellcheck disable=SC2116
cr=$(echo $'\n> ')
cr=${cr%.}

# shellcheck disable=SC2162
read -p "Deseja criar Aplicativo[S/N]: $cr" CONTINUE

# shellcheck disable=SC1009
if [ "$CONTINUE" = "s" ]
then
    cd ..

    # printf "\n\nCleaning ...\n"
    # ./gradlew clean

    # printf "\n\nCleaning build cache ...\n"
    # ./gradlew cleanBuildCache

    # printf "\n\nCreate APK ...\n"
    # ./gradlew assembleDebug


    printf "\n\nCopping...\n"
    mkdir -p "app/src/release" && cp -Rf "app/build/outputs/apk/debug" "app/src/release"
    printf "\n\nSUCCESS\n"

    git add app/src/release

    git status

    git commit -m "Add new release"

    git push -u origin main

    printf "\n\nSUCCESS\n"

else
  printf "\n\nOPERCAO CANCELADA"
fi






