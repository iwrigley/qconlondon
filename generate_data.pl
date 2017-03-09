#!/usr/bin/perl

use DBI;

my $dbh = DBI->connect('DBI:mysql:database=tutorial;host=localhost', 'kafkauser', 'kafka', {RaiseError => 1});

my $sth = $dbh->prepare('INSERT INTO checkins VALUES (NULL, ?, ?, ?)');

while (1) {
	$uid = int(rand(100))+1;
	$vid = int(rand(20))+1;
	$amount = (int(rand(9949))+50)/100;
	$sth->execute($uid, $vid, $amount);
	select(undef,undef,undef,rand(1)); # sleep for a random fraction of a second
}	
