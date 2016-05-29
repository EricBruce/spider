#!/bin/sh

mvn clean package -Dmaven.test.skip


outputFolder="output"

if [ ! -d "$outputFolder" ]; then
    mkdir "$outputFolder"
fi

cp target/spiderFurniture.jar "$outputFolder"

cp src/main/resources/config.properties "$outputFolder"
