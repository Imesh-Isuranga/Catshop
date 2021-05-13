# Catshop System

In this public repository, all source code files can be found for a completed Catshop project. 

This repository will contain the final release of the Catshop system ready for distribution.

# 2020-CI553-catshop
CatShop system for 2020-CI553 (2020-21 cohort)

Initialised from 2019-CI553- catshop Version 1.2.1

# Database Installation and Connection

The file DataBase.txt must contain Derby to work with Derby database

BlueJ
To use the Derby database, you must add in 
Tools -> Preferences -> Libaries
%DERBY_HOME%\lib\derby.jar
%DERBY_HOME%\lib\derbytools.jar

REPLACE %DERBY_HOME% with the path to the base of the Derby database

Eclipse
To use the Derby database, after importing the jd3.tgz archive
Move the file DataBase.txt to be in the toplevel directory of your project 
(with src and the JRE System library)
Copy also the directory Images to the same top level directory as DataBase.txt

Then in Properties -> Resource -> Java Build Path -> Libraries
Select Add external JARs
%DERBY_HOME%\lib\derby.jar

REPLACE %DERBY_HOME% with the path to the base of the Derby database

To create the Derby database, after compiling the system 
run the application Setup in the package clients


Exit BlueJ/Eclipse
Now run the application Main in the package clients
