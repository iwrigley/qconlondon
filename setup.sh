#!/bin/bash
sudo yum -y --disableplugin=refresh-packagekit install mysql-server
sudo service mysqld start
mysql -u root < /home/training/qconlondon/mysql_scripts.sql
sudo cp /home/training/qconlondon/exercise3/solution/mysql-connector-java-5.1.40/mysql-connector-java-5.1.40-bin.jar /usr/share/java/kafka-connect-jdbc

