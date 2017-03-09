create database tutorial;
grant all on tutorial.* to kafkauser@localhost identified by  'kafka';
use tutorial;
create table checkins (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, userID INT, venueID INT, amountSold DOUBLE);

