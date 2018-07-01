#!/bin/bash
solrctl

solrctl instancedir --generate $HOME/configurationLab2

gedit $HOME/configurationLab2/conf/schema.xml

solrctl instancedir --create  lab2 $HOME/configurationLab2

solrctl collection --create lab2
