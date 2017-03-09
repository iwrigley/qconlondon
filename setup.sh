#!/bin/bash
sudo yum -y install mysql-server
sudo service mysqld start
mysql -u root < /home/training/tutorial/mysql_scripts.sql
sudo cp /home/training/tutorial/exercise3/solution/mysql-connector-java-5.1.40/mysql-connector-java-5.1.40-bin.jar /usr/share/java/kafka-connect-jdbc

