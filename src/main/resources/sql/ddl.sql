CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

DROP TABLE IF EXISTS `testdb`.`transactiontype`;
CREATE TABLE `testdb`.`transactiontype` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`));

INSERT into transactiontype (`type`) VALUES ('Credit');
INSERT into transactiontype (`type`) VALUES ('Debit');

DROP TABLE IF EXISTS `testdb`.`transactionstatus`;
CREATE TABLE `testdb`.`transactionstatus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`));

INSERT into transactionstatus (`type`) VALUES ('accepted');
INSERT into transactionstatus (`type`) VALUES ('rejected');
INSERT into transactionstatus (`type`) VALUES ('checking');

DROP TABLE IF EXISTS `testdb`.`users`;
CREATE TABLE `testdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,  
  `phone` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`username`));

DROP TABLE IF EXISTS `testdb`.`users`;
CREATE TABLE `testdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,  
  `phone` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`username`));
  
Select * FROM `testdb`.`users`