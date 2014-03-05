Ace GroepT Leuven - Graduaat Informatica - Ontwerpen met Java
example EclipseLink - Mysql db 
Created for a mysql db name omj2014_t
Author Stijn Heylen

Eclipselink 2.5
Mysql 5.1

Database omj2014_t should exist:
delimiter $$

CREATE DATABASE `omj2014_t` /*!40100 DEFAULT CHARACTER SET latin1 */$$

Table will be generated like this if not exists:
delimiter $$

CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT b'1',
  `CITY` varchar(255) NOT NULL,
  `COUNTRY` varchar(100) DEFAULT 'BE',
  `HNUMBER` varchar(20) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `STREET` varchar(255) NOT NULL,
  `ZIPCODE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `contact` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTACT` varchar(255) NOT NULL,
  `TYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTACT_TYPE_ID` (`TYPE_ID`),
  CONSTRAINT `FK_CONTACT_TYPE_ID` FOREIGN KEY (`TYPE_ID`) REFERENCES `contacttype` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `contacttype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(100) DEFAULT NULL,
  `LNAME` varchar(100) DEFAULT NULL,
  `VATNR` varchar(20) DEFAULT NULL,
  `INVOICEADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CUSTOMER_INVOICEADDRESS_ID` (`INVOICEADDRESS_ID`),
  CONSTRAINT `FK_CUSTOMER_INVOICEADDRESS_ID` FOREIGN KEY (`INVOICEADDRESS_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `customer_address` (
  `Customer_ID` int(11) NOT NULL,
  `deliveryAddresses_ID` int(11) NOT NULL,
  PRIMARY KEY (`Customer_ID`,`deliveryAddresses_ID`),
  KEY `FK_CUSTOMER_ADDRESS_deliveryAddresses_ID` (`deliveryAddresses_ID`),
  CONSTRAINT `FK_CUSTOMER_ADDRESS_Customer_ID` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`ID`),
  CONSTRAINT `FK_CUSTOMER_ADDRESS_deliveryAddresses_ID` FOREIGN KEY (`deliveryAddresses_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `customer_contact` (
  `Customer_ID` int(11) NOT NULL,
  `contactDetails_ID` int(11) NOT NULL,
  PRIMARY KEY (`Customer_ID`,`contactDetails_ID`),
  KEY `FK_CUSTOMER_CONTACT_contactDetails_ID` (`contactDetails_ID`),
  CONSTRAINT `FK_CUSTOMER_CONTACT_contactDetails_ID` FOREIGN KEY (`contactDetails_ID`) REFERENCES `contact` (`ID`),
  CONSTRAINT `FK_CUSTOMER_CONTACT_Customer_ID` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(100) DEFAULT NULL,
  `LASTNAME` varchar(100) DEFAULT NULL,
  `HOMEADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EMPLOYEE_HOMEADDRESS_ID` (`HOMEADDRESS_ID`),
  CONSTRAINT `FK_EMPLOYEE_HOMEADDRESS_ID` FOREIGN KEY (`HOMEADDRESS_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `employee_contact` (
  `Employee_ID` int(11) NOT NULL,
  `contactDetails_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`,`contactDetails_ID`),
  KEY `FK_EMPLOYEE_CONTACT_contactDetails_ID` (`contactDetails_ID`),
  CONSTRAINT `FK_EMPLOYEE_CONTACT_contactDetails_ID` FOREIGN KEY (`contactDetails_ID`) REFERENCES `contact` (`ID`),
  CONSTRAINT `FK_EMPLOYEE_CONTACT_Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `servicecall` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLOSED` datetime DEFAULT NULL,
  `LONGDESCRIPTION` varchar(255) NOT NULL,
  `OPENED` datetime NOT NULL,
  `SHORTDESCRIPTION` varchar(100) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `RESPONSIBLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SERVICECALL_RESPONSIBLE_ID` (`RESPONSIBLE_ID`),
  KEY `FK_SERVICECALL_CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `FK_SERVICECALL_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`),
  CONSTRAINT `FK_SERVICECALL_RESPONSIBLE_ID` FOREIGN KEY (`RESPONSIBLE_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1$$


