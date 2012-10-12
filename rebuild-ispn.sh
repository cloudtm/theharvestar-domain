#!/bin/bash

ant clean && ant -f build_ispn.xml clean-all && ant -f build_ispn.xml && ant compile && ant gen-jar
