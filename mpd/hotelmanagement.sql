# Drop any such existing DB
DROP DATABASE IF EXISTS hotelmanagement;

# Create the DB
Create database hotelmanagement;

# Select the DB
USE hotelmanagement;

# Table for Country
CREATE TABLE hotelmanagement.country(
	id INTEGER(5) NOT NULL AUTO_INCREMENT,
	countryCode INTEGER(5) NOT NULL,
	countryName VARCHAR(50) NOT NULL,
	CONSTRAINT UK_COUNTRY_CODE UNIQUE KEY (countryCode),
	CONSTRAINT UK_COUNTRY_COUNTRY_NAME UNIQUE KEY (countryName),
	CONSTRAINT UK_COUNTRY_COUNTRY_NAME_CODE UNIQUE KEY (countryCode, countryName),
	PRIMARY KEY (id)
);

CREATE TABLE hotelmanagement.states(
	id INTEGER(5) NOT NULL AUTO_INCREMENT,
	countryId INTEGER(5) NOT NULL,
	stateName VARCHAR(50) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_STATES_COUNTRY_ID FOREIGN KEY  (countryId) REFERENCES hotelmanagement.country(id)
);

CREATE TABLE hotelmanagement.postalcodes(
	id INTEGER(5) NOT NULL AUTO_INCREMENT,
	stateId INTEGER(5) NOT NULL,
	postalCode INTEGER(6) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_POSTALCODES_STATE_ID FOREIGN KEY  (stateId) REFERENCES hotelmanagement.states(id)	
);

CREATE TABLE hotelmanagement.city(
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	postalCode INTEGER(5) NOT NULL,
	cityName VARCHAR(50) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_CITY_POSTAL_CODE FOREIGN KEY  (postalCode) REFERENCES hotelmanagement.postalcodes(id)	
);

CREATE TABLE hotelmanagement.refaddresstype(
	id INTEGER(3) NOT NULL AUTO_INCREMENT,
	addressTypeDesc VARCHAR(50),
	PRIMARY KEY (id)
);


CREATE TABLE hotelmanagement.addresses(
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	line1 VARCHAR(50),
	line2 VARCHAR(50),
	line3 VARCHAR(50),
	addresstype INTEGER(10),
	cityId INTEGER(10),
	postalId INTEGER(5),
	stateId INTEGER(5),
	countryId INTEGER(5),
	PRIMARY KEY (id),
	CONSTRAINT FK_ADDRESSES_ADDRESSTYPE FOREIGN KEY (addresstype) REFERENCES hotelmanagement.refaddresstype(id),
	CONSTRAINT FK_ADDRESSES_CITY FOREIGN KEY (cityId) REFERENCES hotelmanagement.city(id),
	CONSTRAINT FK_ADDRESSES_POSTAL FOREIGN KEY (postalId) REFERENCES hotelmanagement.postalcodes(id),
	CONSTRAINT FK_ADDRESSES_STATES FOREIGN KEY (stateId) REFERENCES hotelmanagement.states(id),
	CONSTRAINT FK_ADDRESSES_COUNTRY FOREIGN KEY (countryId) REFERENCES hotelmanagement.country(id)	
);




CREATE TABLE hotelmanagement.roles (
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	roleName varchar(20) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT UK_ROLES_ROLESNAME UNIQUE KEY (roleName)
);

CREATE TABLE hotelmanagement.alert (
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	alertType varchar(20) NOT NULL,
	alertName varchar(20) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT UK_STATUS_ALERTTYPE UNIQUE KEY (alertType)
);


CREATE TABLE hotelmanagement.userinformation(
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(50) NOT NULL,
	lastName VARCHAR(50),
	mobileNumber BIGINT(12),
	email VARCHAR(50),
	age INTEGER(3),
	addressId INTEGER(10),
	isCustomer  char(1) DEFAULT 'F',
	PRIMARY KEY (id),
	CONSTRAINT UK_USERINFORMATION_MOBILE UNIQUE KEY (mobileNumber),
	CONSTRAINT UK_USERINFORMATION_EMAIL UNIQUE KEY (email),
	CONSTRAINT FK_USERINFORMATION_ADDRESS FOREIGN KEY(addressId) REFERENCES hotelmanagement.addresses(id)
);


CREATE TABLE hotelmanagement.userlogin(
	id INTEGER(10) NOT NULL AUTO_INCREMENT,
	userid INTEGER(10) NOT NULL,
	roleid INTEGER(2) NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(100) NOT NULL,
	createdDate Date NOT NULL,
	status char(1) DEFAULT 'A',
	lastModifiedDate Date,
	deactivationDate Date,
	lastLoginTime Date,
	PRIMARY KEY(id),
	CONSTRAINT UK_USERLOGIN_NAME UNIQUE KEY (username),
	CONSTRAINT FK_USERLOGIN_ID FOREIGN KEY  (userid) REFERENCES hotelmanagement.userinformation(id),
	CONSTRAINT FK_USERINFORMATION_ROLE_ID FOREIGN KEY (roleid) REFERENCES roles(id)
);

INSERT INTO  `hotelmanagement`.`country` (`countryCode` ,`countryName`) VALUES ( '91',  'India');
INSERT INTO  `hotelmanagement`.`states` (`countryId` ,`stateName`) VALUES ( '1',  'Uttar Pradesh'), ('1',  'Delhi');
INSERT INTO `hotelmanagement`.`postalcodes` ( `stateId`, `postalCode`) VALUES ('1', '201301');
INSERT INTO `hotelmanagement`.`refaddresstype` (`id`, `addressTypeDesc`) VALUES (NULL, 'Primary');
INSERT INTO `hotelmanagement`.`city` (`id`, `postalCode`, `cityName`) VALUES (NULL, '1', 'Noida');
INSERT INTO `hotelmanagement`.`addresses` (`id`, `line1`, `line2`, `line3`, `addresstype`, `cityId`, `postalId`, `stateId`, `countryId`) VALUES (NULL, 'C 408', 'Sector 22', NULL, '1', '1', '1', '1', '1');
INSERT INTO `hotelmanagement`.`userinformation` (`id`, `firstName`, `lastName`, `mobileNumber`, `email`, `age`, `addressId`, `isCustomer`) VALUES (NULL, 'Piyush', 'Gupta', '9990070809', 'piyush035@gmail.com', '26', '1', 'Y');
INSERT INTO `hotelmanagement`.`roles` (`id`, `roleName`) VALUES (NULL, 'admin'), (NULL, 'front desk user');
//INSERT INTO `hotelmanagement`.`alert` (`id`, `alertType`, `alertName`) VALUES (NULL, 'alert','Kitchen'), (NULL, 'front desk','House Keeping');

INSERT INTO `hotelmanagement`.`userlogin` (`id`, `userid`, `roleid`, `username`, `password`, `createdDate`, `status`, `lastModifiedDate`, `deactivationDate`, `lastLoginTime`) VALUES (NULL, '1', '1', 'piyush', 'admin', '2014-02-02', 'A', '2014-02-02', NULL, NULL);