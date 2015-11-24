use main;
CREATE DATABASE IF NOT EXISTS main default charset utf8 COLLATE utf8_general_ci;
CREATE TABLE IF NOT EXISTS users(
	id INT(10) not null AUTO_INCREMENT PRIMARY KEY, 
	username varchar(50) not null, 
	password varchar(50) not null, 
	age varchar(50), gender varchar(50) not null, 
	phone varchar(50) not null, 
	email varchar(50) not null, 
	address varchar(50) not null,  
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  
	enabled SMALLINT default 1
);
CREATE TABLE IF NOT EXISTS authorities(
	id INT(10) not null AUTO_INCREMENT PRIMARY KEY, 
	username varchar(50) not null, 
	authority varchar(50) not null
);