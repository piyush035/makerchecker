CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

DROP TABLE IF EXISTS `testdb`.`transactiontype`;
CREATE TABLE `testdb`.`transactiontype` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`));

INSERT into transactiontype (`id`,`type`) VALUES (1,'Credit');
INSERT into transactiontype (`id`,`type`) VALUES (2,'Debit');

DROP TABLE IF EXISTS `testdb`.`transactionstatus`;
CREATE TABLE IF NOT EXISTS `testdb`.`transactionstatus` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`));

INSERT into transactionstatus (`id`,`type`) VALUES (1,'accepted');
INSERT into transactionstatus (`id`,`type`) VALUES (2,'rejected');
INSERT into transactionstatus (`id`,`type`) VALUES (3,'pending');

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
  
DROP TABLE IF EXISTS `testdb`.`transaction`;
CREATE TABLE IF NOT EXISTS `testdb`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `accountNumber` INT(45) NULL,
  `type` INT NOT NULL,
  `status` INT NOT NULL,
  `amount` DECIMAL(45) NULL,
  `comment` VARCHAR(100) NULL,  
  `userid` INT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`type`) REFERENCES transactiontype(`id`),
  FOREIGN KEY (`status`) REFERENCES transactionstatus(`id`),
  FOREIGN KEY (`userid`) REFERENCES users(`id`));
  
