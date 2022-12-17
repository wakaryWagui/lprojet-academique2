#!/bin/sh
find -name "*.java" > sources.txt
javac -encoding ISO-8859-1 -d ../build -cp :* @sources.txt
java -cp ../build welcome.Main
