-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sms
--

CREATE DATABASE IF NOT EXISTS sms;
USE sms;

--
-- Definition of table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `accountExpired` char(1) default 'N',
  `accountLocked` char(1) default 'N',
  `credentialsExpired` char(1) default 'N',
  `custId` bigint(20) NOT NULL,
  `accountEnabled` char(1) default 'Y',
  `password` varchar(128) NOT NULL,
  `passwordHint` varchar(255) default NULL,
  `sharePrivacy` char(1) default 'N',
  `userOnlineNow` char(1) default 'N',
  `username` varchar(128) NOT NULL,
  `caddressId` bigint(20) default NULL,
  `personId` bigint(20) default NULL,
  `paddressId` bigint(20) default NULL,
  `pcontactId` bigint(20) default NULL,
  `imageId` bigint(20) default NULL,
  `scontactId` bigint(20) default NULL,
  `taddressId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK1D0C220D9D5F05F0` (`personId`),
  KEY `FK1D0C220D36896FA8` (`paddressId`),
  KEY `FK1D0C220D7AF3424` (`taddressId`),
  KEY `FK1D0C220DD5BBFC14` (`scontactId`),
  KEY `FK1D0C220DFF1AE8DB` (`imageId`),
  KEY `FK1D0C220DF8DFA8B7` (`pcontactId`),
  KEY `FK1D0C220DCECEB115` (`caddressId`),
  CONSTRAINT `FK1D0C220D36896FA8` FOREIGN KEY (`paddressId`) REFERENCES `address` (`id`),
  CONSTRAINT `FK1D0C220D7AF3424` FOREIGN KEY (`taddressId`) REFERENCES `address` (`id`),
  CONSTRAINT `FK1D0C220D9D5F05F0` FOREIGN KEY (`personId`) REFERENCES `person` (`id`),
  CONSTRAINT `FK1D0C220DCECEB115` FOREIGN KEY (`caddressId`) REFERENCES `address` (`id`),
  CONSTRAINT `FK1D0C220DD5BBFC14` FOREIGN KEY (`scontactId`) REFERENCES `contacts` (`id`),
  CONSTRAINT `FK1D0C220DF8DFA8B7` FOREIGN KEY (`pcontactId`) REFERENCES `contacts` (`id`),
  CONSTRAINT `FK1D0C220DFF1AE8DB` FOREIGN KEY (`imageId`) REFERENCES `userimage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,'N','N','N',1,'Y','eee417ade1aaabe13200a63d02c841130be82051',NULL,'N','N','narahari@uroomtech.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,NULL,NULL,NULL,NULL,NULL,1,'N','N','N',1,'Y','eee417ade1aaabe13200a63d02c841130be82051',NULL,'N','N','narahari@urt.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,NULL,NULL,NULL,NULL,NULL,1,'N','N','N',1,'Y','eee417ade1aaabe13200a63d02c841130be82051',NULL,'N','N','oakstudent1@urt.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,NULL,NULL,NULL,NULL,NULL,1,'N','N','N',1,'Y','eee417ade1aaabe13200a63d02c841130be82051',NULL,'N','N','oakclerk1@urt.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


--
-- Definition of table `accountassociatedperson`
--

DROP TABLE IF EXISTS `accountassociatedperson`;
CREATE TABLE `accountassociatedperson` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `dateOfBirth` datetime default NULL,
  `descriptionLine1` varchar(255) default NULL,
  `descriptionLine2` varchar(255) default NULL,
  `descriptionLine3` varchar(255) default NULL,
  `formalName` varchar(255) default NULL,
  `picFilePath` varchar(255) default NULL,
  `relationship` varchar(64) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountassociatedperson`
--

/*!40000 ALTER TABLE `accountassociatedperson` DISABLE KEYS */;
/*!40000 ALTER TABLE `accountassociatedperson` ENABLE KEYS */;


--
-- Definition of table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `accuracy` varchar(32) default NULL,
  `addressLine1` varchar(128) default NULL,
  `addressLine2` varchar(64) default NULL,
  `addressLine3` varchar(64) default NULL,
  `addressType` varchar(1) default NULL,
  `addressUsage` varchar(16) default NULL,
  `apartmentNumber` varchar(10) default NULL,
  `boxNumber` varchar(10) default NULL,
  `city` varchar(64) default NULL,
  `country` varchar(64) default NULL,
  `distance` varchar(32) default NULL,
  `duration` datetime default NULL,
  `effectiveDate` datetime default NULL,
  `email` varchar(128) default NULL,
  `endDate` datetime default NULL,
  `latitude` varchar(32) default NULL,
  `longitude` varchar(32) default NULL,
  `postalCode` varchar(12) default NULL,
  `ruralRoute` varchar(64) default NULL,
  `state` varchar(3) default NULL,
  `streetName` varchar(128) default NULL,
  `streetNumber` varchar(10) default NULL,
  `streetPostDirection` longtext,
  `streetType` varchar(64) default NULL,
  `suiteNumber` varchar(64) default NULL,
  `timeDuration` varchar(32) default NULL,
  `url` longtext,
  `zipCodeSupplement` varchar(6) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


--
-- Definition of table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `name` varchar(124) default NULL,
  `filePath` longtext,
  `fileSize` longtext,
  `fileType` varchar(40) default NULL,
  `fileTypePath` longtext,
  `fileUsed` varchar(50) default NULL,
  `mapFile` varchar(255) default NULL,
  `thumbNail` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--

/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


--
-- Definition of table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `attendanceDate` datetime default NULL,
  `status` varchar(255) default NULL,
  `periodId` bigint(20) default NULL,
  `studentId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7117E2E9E7B53D7A` (`periodId`),
  KEY `FK7117E2E93006E154` (`studentId`),
  CONSTRAINT `FK7117E2E93006E154` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`),
  CONSTRAINT `FK7117E2E9E7B53D7A` FOREIGN KEY (`periodId`) REFERENCES `period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;


--
-- Definition of table `calendarevent`
--

DROP TABLE IF EXISTS `calendarevent`;
CREATE TABLE `calendarevent` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `accountId` bigint(20) default NULL,
  `custId` bigint(20) default NULL,
  `endDate` datetime default NULL,
  `message` longtext,
  `startDate` datetime default NULL,
  `title` varchar(128) default NULL,
  `addressId` bigint(20) default NULL,
  `categoryId` bigint(20) default NULL,
  `recId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7395DDFC865BD9C1` (`recId`),
  KEY `FK7395DDFC22193C3` (`categoryId`),
  KEY `FK7395DDFC5667F218` (`addressId`),
  CONSTRAINT `FK7395DDFC22193C3` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  CONSTRAINT `FK7395DDFC5667F218` FOREIGN KEY (`addressId`) REFERENCES `address` (`id`),
  CONSTRAINT `FK7395DDFC865BD9C1` FOREIGN KEY (`recId`) REFERENCES `recevent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendarevent`
--

/*!40000 ALTER TABLE `calendarevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarevent` ENABLE KEYS */;


--
-- Definition of table `calendarsettings`
--

DROP TABLE IF EXISTS `calendarsettings`;
CREATE TABLE `calendarsettings` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `calendarView` varchar(255) default NULL,
  `custId` bigint(20) NOT NULL,
  `eventsAddress` varchar(255) default NULL,
  `eventsThreshold` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendarsettings`
--

/*!40000 ALTER TABLE `calendarsettings` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarsettings` ENABLE KEYS */;


--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `custId` bigint(20) default NULL,
  `description` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `classsubject`
--

DROP TABLE IF EXISTS `classsubject`;
CREATE TABLE `classsubject` (
  `classId` bigint(20) NOT NULL,
  `subjectId` bigint(20) NOT NULL,
  PRIMARY KEY  (`classId`,`subjectId`),
  KEY `FK9C9580B443E3F45F` (`classId`),
  KEY `FK9C9580B44875E9C7` (`subjectId`),
  CONSTRAINT `FK9C9580B443E3F45F` FOREIGN KEY (`classId`) REFERENCES `studyclass` (`id`),
  CONSTRAINT `FK9C9580B44875E9C7` FOREIGN KEY (`subjectId`) REFERENCES `studysubject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classsubject`
--

/*!40000 ALTER TABLE `classsubject` DISABLE KEYS */;
/*!40000 ALTER TABLE `classsubject` ENABLE KEYS */;


--
-- Definition of table `classteacher`
--

DROP TABLE IF EXISTS `classteacher`;
CREATE TABLE `classteacher` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `academicYear` varchar(255) default NULL,
  `ct` varchar(255) default NULL,
  `custId` bigint(20) NOT NULL,
  `teacherId` bigint(20) default NULL,
  `studyClassId` bigint(20) default NULL,
  `studySubjectId` bigint(20) default NULL,
  `score` int(11) default NULL,
  `student` tinyblob,
  `testType` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKAC51A34AF6B34E1E` (`studySubjectId`),
  KEY `FKAC51A34ACCB62A0` (`teacherId`),
  KEY `FKAC51A34ADA0C8276` (`studyClassId`),
  CONSTRAINT `FKAC51A34ACCB62A0` FOREIGN KEY (`teacherId`) REFERENCES `staff` (`id`),
  CONSTRAINT `FKAC51A34ADA0C8276` FOREIGN KEY (`studyClassId`) REFERENCES `studyclass` (`id`),
  CONSTRAINT `FKAC51A34AF6B34E1E` FOREIGN KEY (`studySubjectId`) REFERENCES `studysubject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classteacher`
--

/*!40000 ALTER TABLE `classteacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `classteacher` ENABLE KEYS */;


--
-- Definition of table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `areaCode` varchar(255) default NULL,
  `domestic` bit(1) NOT NULL,
  `extension` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `foreignPhoneNumber` varchar(255) default NULL,
  `phoneNumberPrefix` varchar(255) default NULL,
  `phoneNumberSuffix` varchar(255) default NULL,
  `usageType` varchar(10) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contacts`
--

/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `accountType` varchar(5) default NULL,
  `constantContactAPI` varchar(250) default NULL,
  `constantContactPW` varchar(250) default NULL,
  `constantContactUN` varchar(250) default NULL,
  `custEmail` varchar(128) NOT NULL,
  `customerName` varchar(128) default NULL,
  `customerStatus` varchar(5) default 'A',
  `firstName` varchar(128) default NULL,
  `lastName` varchar(128) default NULL,
  `mailChimpAPI` varchar(250) default NULL,
  `mailChimpPW` varchar(250) default NULL,
  `mailChimpUN` varchar(250) default NULL,
  `organization` varchar(128) default NULL,
  `status` char(1) NOT NULL default 'Y',
  `subscriptionType` varchar(5) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `custEmail` (`custEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'narahari@uroomtech.com','Oakhills','A',NULL,NULL,NULL,NULL,NULL,'Narahari School','Y',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `emailtemplate`
--

DROP TABLE IF EXISTS `emailtemplate`;
CREATE TABLE `emailtemplate` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `footer` longtext,
  `fromEmail` varchar(255) default NULL,
  `messageBody` longtext,
  `salutation` varchar(255) default NULL,
  `subject` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emailtemplate`
--

/*!40000 ALTER TABLE `emailtemplate` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailtemplate` ENABLE KEYS */;


--
-- Definition of table `fee`
--

DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `custId` bigint(20) NOT NULL,
  `description` varchar(255) default NULL,
  `endMonth` varchar(255) default NULL,
  `paymentAmount` double NOT NULL,
  `paymentType` varchar(255) default NULL,
  `startMonth` varchar(255) default NULL,
  `termCount` int(11) NOT NULL,
  `type` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fee`
--

/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;


--
-- Definition of table `paymenttransactions`
--

DROP TABLE IF EXISTS `paymenttransactions`;
CREATE TABLE `paymenttransactions` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `amount` varchar(45) default NULL,
  `customerId` bigint(20) default NULL,
  `payerId` varchar(45) default NULL,
  `productId` varchar(255) default NULL,
  `profileId` varchar(45) default NULL,
  `profileStatus` varchar(45) default NULL,
  `token` varchar(45) default NULL,
  `tanDate` datetime default NULL,
  `transactionId` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymenttransactions`
--

/*!40000 ALTER TABLE `paymenttransactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymenttransactions` ENABLE KEYS */;


--
-- Definition of table `period`
--

DROP TABLE IF EXISTS `period`;
CREATE TABLE `period` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `academicYear` varchar(255) default NULL,
  `custId` bigint(20) NOT NULL,
  `description` varchar(255) default NULL,
  `endTime` varchar(255) default NULL,
  `periodCount` bigint(20) NOT NULL,
  `startTime` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  `classId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKC4E375C143E3F45F` (`classId`),
  CONSTRAINT `FKC4E375C143E3F45F` FOREIGN KEY (`classId`) REFERENCES `studyclass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `period`
--

/*!40000 ALTER TABLE `period` DISABLE KEYS */;
/*!40000 ALTER TABLE `period` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `citizenship` varchar(32) default NULL,
  `studyClass` varchar(255) default NULL,
  `dateOfBirth` varchar(12) default NULL,
  `empId` varchar(10) default NULL,
  `firstName` varchar(128) default NULL,
  `gender` varchar(1) default NULL,
  `height` double default NULL,
  `lastName` varchar(128) default NULL,
  `maritalStatus` varchar(32) default NULL,
  `middleName` varchar(64) default NULL,
  `mothersMaidenName` varchar(32) default NULL,
  `passwordHint` varchar(255) default NULL,
  `passportExpireDate` datetime default NULL,
  `passportNumber` varchar(16) default NULL,
  `personalTitle` varchar(10) default NULL,
  `socialSecurityNumber` varchar(14) default NULL,
  `suffix` varchar(20) default NULL,
  `weight` double default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


--
-- Definition of table `productprice`
--

DROP TABLE IF EXISTS `productprice`;
CREATE TABLE `productprice` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `productCost` double NOT NULL,
  `productLevelId` bigint(20) default NULL,
  `subscriptionType` varchar(2) NOT NULL,
  `productId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3BA4B19A6F64731B` (`productId`),
  CONSTRAINT `FK3BA4B19A6F64731B` FOREIGN KEY (`productId`) REFERENCES `urtproduct` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productprice`
--

/*!40000 ALTER TABLE `productprice` DISABLE KEYS */;
/*!40000 ALTER TABLE `productprice` ENABLE KEYS */;


--
-- Definition of table `productsublevels`
--

DROP TABLE IF EXISTS `productsublevels`;
CREATE TABLE `productsublevels` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `levelName` varchar(45) default NULL,
  `monthCost` double default NULL,
  `productId` bigint(20) default NULL,
  `yearCost` double default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productsublevels`
--

/*!40000 ALTER TABLE `productsublevels` DISABLE KEYS */;
/*!40000 ALTER TABLE `productsublevels` ENABLE KEYS */;


--
-- Definition of table `recevent`
--

DROP TABLE IF EXISTS `recevent`;
CREATE TABLE `recevent` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `endDate` datetime default NULL,
  `eventLength` bigint(20) default NULL,
  `eventPid` bigint(20) NOT NULL,
  `recType` varchar(30) default NULL,
  `repeatOn` varchar(60) default NULL,
  `startDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recevent`
--

/*!40000 ALTER TABLE `recevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `recevent` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `description` varchar(128) default NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,'Administrator','ROLE_ADMIN'),
 (2,NULL,NULL,NULL,NULL,NULL,1,'Teacher','ROLE_TEACHER'),
 (3,NULL,NULL,NULL,NULL,NULL,1,'Student','ROLE_STUDENT'),
 (4,NULL,NULL,NULL,NULL,NULL,1,'Clerk','ROLE_CLERK');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `signupaccount`
--

DROP TABLE IF EXISTS `signupaccount`;
CREATE TABLE `signupaccount` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `accuracy` varchar(32) default NULL,
  `amountWithOutTax` double NOT NULL,
  `city` varchar(255) default NULL,
  `expressCheckoutToken` varchar(255) default NULL,
  `firstName` varchar(128) default NULL,
  `lastName` varchar(128) default NULL,
  `latitude` varchar(32) default NULL,
  `longitude` varchar(32) default NULL,
  `nextPaymentDate` varchar(255) default NULL,
  `organization` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `payerId` varchar(255) default NULL,
  `payerStatus` varchar(255) default NULL,
  `paymentType` varchar(255) default NULL,
  `phoneNumber` varchar(255) default NULL,
  `postalCode` varchar(255) default NULL,
  `productIds` varchar(50) default NULL,
  `productNames` varchar(128) default NULL,
  `profileId` varchar(255) default NULL,
  `profileStartDate` varchar(255) default NULL,
  `profileStatus` varchar(255) default NULL,
  `renewDate` varchar(255) default NULL,
  `state` varchar(255) default NULL,
  `status` varchar(2) default NULL,
  `streetPostDirection` varchar(255) default NULL,
  `subscriptionType` varchar(10) default NULL,
  `taxPercentage` double NOT NULL,
  `transactionId` varchar(255) default NULL,
  `transactionType` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signupaccount`
--

/*!40000 ALTER TABLE `signupaccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `signupaccount` ENABLE KEYS */;


--
-- Definition of table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `academicYear` varchar(255) default NULL,
  `accountId` bigint(20) NOT NULL,
  `custId` bigint(20) NOT NULL,
  `qualification` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES  (1,NULL,NULL,NULL,NULL,NULL,NULL,'2010-2011',2,1,'BSC','A'),
 (2,NULL,NULL,NULL,NULL,NULL,1,'2010-2011',4,1,'10th','A');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;


--
-- Definition of table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `stateCode` varchar(20) default NULL,
  `stateName` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

/*!40000 ALTER TABLE `state` DISABLE KEYS */;
/*!40000 ALTER TABLE `state` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `academicYear` varchar(255) default NULL,
  `accountId` bigint(20) NOT NULL,
  `classId` bigint(20) NOT NULL,
  `custId` bigint(20) NOT NULL,
  `rollNumber` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,'2010-2011',2,1,1,'1','A');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


--
-- Definition of table `studyclass`
--

DROP TABLE IF EXISTS `studyclass`;
CREATE TABLE `studyclass` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `className` varchar(255) default NULL,
  `custId` bigint(20) NOT NULL,
  `description` varchar(255) default NULL,
  `section` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studyclass`
--

/*!40000 ALTER TABLE `studyclass` DISABLE KEYS */;
INSERT INTO `studyclass` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,'1',1,'First Class','A'),
 (2,NULL,NULL,NULL,NULL,NULL,1,'1',1,'First Class','B'),
 (3,NULL,NULL,NULL,NULL,NULL,1,'2',1,'Second Class','A'),
 (4,NULL,NULL,NULL,NULL,NULL,1,'2',1,'Second Class','B'),
 (5,NULL,NULL,NULL,NULL,NULL,1,'3',1,'Third Class','A'),
 (6,NULL,NULL,NULL,NULL,NULL,1,'3',1,'Third Class','B'),
 (7,NULL,NULL,NULL,NULL,NULL,1,'4',1,'Fourth Class','A'),
 (8,NULL,NULL,NULL,NULL,NULL,1,'4',1,'Fourth Class','B'),
 (9,NULL,NULL,NULL,NULL,NULL,1,'5',1,'Fifth Class','A'),
 (10,NULL,NULL,NULL,NULL,NULL,1,'5',1,'Fifth Class','B'),
 (11,NULL,NULL,NULL,NULL,NULL,1,'6',1,'Sixth Class','A'),
 (12,NULL,NULL,NULL,NULL,NULL,1,'6',1,'Sixth Class','B'),
 (13,NULL,NULL,NULL,NULL,NULL,1,'7',1,'Seventh Class','A'),
 (14,NULL,NULL,NULL,NULL,NULL,1,'7',1,'Seventh Class','B'),
 (15,NULL,NULL,NULL,NULL,NULL,1,'8',1,'Eighth Class','A'),
 (16,NULL,NULL,NULL,NULL,NULL,1,'8',1,'Eighth Class','B'),
 (17,NULL,NULL,NULL,NULL,NULL,1,'9',1,'Ninth Class','A'),
 (18,NULL,NULL,NULL,NULL,NULL,1,'9',1,'Ninth Class','B'),
 (19,NULL,NULL,NULL,NULL,NULL,1,'10',1,'Tenth Class','A'),
 (20,NULL,NULL,NULL,NULL,NULL,1,'10',1,'Tenth Class','B');
/*!40000 ALTER TABLE `studyclass` ENABLE KEYS */;


--
-- Definition of table `studysubject`
--

DROP TABLE IF EXISTS `studysubject`;
CREATE TABLE `studysubject` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `custId` bigint(20) NOT NULL,
  `description` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studysubject`
--

/*!40000 ALTER TABLE `studysubject` DISABLE KEYS */;
INSERT INTO `studysubject` VALUES  (1,NULL,NULL,NULL,NULL,NULL,1,1,'English','ENGLISH'),
 (2,NULL,NULL,NULL,NULL,NULL,1,1,'Hindi','HINDI'),
 (3,NULL,NULL,NULL,NULL,NULL,1,1,'Telugu','TELUGU'),
 (4,NULL,NULL,NULL,NULL,NULL,1,1,'Science','SCIENCE'),
 (5,NULL,NULL,NULL,NULL,NULL,1,1,'Social','SOCIAL'),
 (6,NULL,NULL,NULL,NULL,NULL,1,1,'Maths','MATHEMATICS'),
 (7,NULL,NULL,NULL,NULL,NULL,1,1,'Sanskrit','SANSKRIT'),
 (8,NULL,NULL,NULL,NULL,NULL,1,1,'Physical Science','PHYSICALSCIENCE'),
 (9,NULL,NULL,NULL,NULL,NULL,1,1,'Natural Science','NATURALSCIENCE'),
 (10,NULL,NULL,NULL,NULL,NULL,1,1,'Economics','ECONOMICS'),
 (11,NULL,NULL,NULL,NULL,NULL,1,1,'Geography','GEOGRAPHY'),
 (12,NULL,NULL,NULL,NULL,NULL,1,1,'Civics','CIVICS'),
 (13,NULL,NULL,NULL,NULL,NULL,1,1,'History','HISTORY');
/*!40000 ALTER TABLE `studysubject` ENABLE KEYS */;


--
-- Definition of table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `amount` double default NULL,
  `nextPaymentDate` datetime default NULL,
  `paymentStatus` varchar(5) default NULL,
  `productId` bigint(20) NOT NULL,
  `profileId` varchar(128) default NULL,
  `profileStartDate` datetime default NULL,
  `status` varchar(5) NOT NULL default 'Y',
  `subscriptionType` varchar(2) default NULL,
  `userId` bigint(20) default NULL,
  `custId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK1456591D1EE54C84` (`custId`),
  CONSTRAINT `FK1456591D1EE54C84` FOREIGN KEY (`custId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscription`
--

/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;


--
-- Definition of table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `custId` bigint(20) NOT NULL,
  `endTime` varchar(255) default NULL,
  `entryDate` datetime default NULL,
  `startTime` varchar(255) default NULL,
  `classTeacherId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK18BB101C25630AC` (`classTeacherId`),
  CONSTRAINT `FK18BB101C25630AC` FOREIGN KEY (`classTeacherId`) REFERENCES `classteacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timetable`
--

/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;


--
-- Definition of table `transport`
--

DROP TABLE IF EXISTS `transport`;
CREATE TABLE `transport` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `boardingPoints` varchar(255) default NULL,
  `custId` bigint(20) NOT NULL,
  `driver` varchar(255) default NULL,
  `helper` varchar(255) default NULL,
  `routeDescription` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  `vehicleNumber` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transport`
--

/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;


--
-- Definition of table `urtproduct`
--

DROP TABLE IF EXISTS `urtproduct`;
CREATE TABLE `urtproduct` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `link` varchar(255) NOT NULL,
  `menuName` varchar(255) NOT NULL,
  `name` varchar(128) NOT NULL,
  `role` varchar(255) default NULL,
  `status` char(1) NOT NULL default 'Y',
  `urlName` varchar(128) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `urtproduct`
--

/*!40000 ALTER TABLE `urtproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `urtproduct` ENABLE KEYS */;


--
-- Definition of table `userimage`
--

DROP TABLE IF EXISTS `userimage`;
CREATE TABLE `userimage` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) default NULL,
  `createdDate` datetime default NULL,
  `lastAccessDate` datetime default NULL,
  `lastUpdatedBy` varchar(255) default NULL,
  `lastUpdatedDate` datetime default NULL,
  `version` int(11) default NULL,
  `mapFile` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `path` longtext,
  `size` bigint(20) default NULL,
  `thumbNail` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `typePath` varchar(255) default NULL,
  `usageType` char(1) NOT NULL default 'Y',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userimage`
--

/*!40000 ALTER TABLE `userimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `userimage` ENABLE KEYS */;


--
-- Definition of table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
CREATE TABLE `userroles` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`userId`,`roleId`),
  KEY `FK8AF57992B6DDEAB2` (`roleId`),
  KEY `FK8AF57992BC33401C` (`userId`),
  CONSTRAINT `FK8AF57992B6DDEAB2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `FK8AF57992BC33401C` FOREIGN KEY (`userId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userroles`
--

/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES  (1,1),
 (2,2),
 (3,3),
 (4,4);
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;


--
-- Definition of table `vw_productdetailsbycustomer`
--

DROP TABLE IF EXISTS `vw_productdetailsbycustomer`;
CREATE TABLE `vw_productdetailsbycustomer` (
  `productId` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `custId` bigint(20) NOT NULL,
  `nextPaymentDate` datetime default NULL,
  `productLink` varchar(255) default NULL,
  `productName` varchar(250) default NULL,
  `status` varchar(5) NOT NULL default 'Y',
  `subscriptionId` bigint(20) default NULL,
  `subscriptionType` varchar(250) default NULL,
  `urlName` varchar(255) default NULL,
  PRIMARY KEY  (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vw_productdetailsbycustomer`
--

/*!40000 ALTER TABLE `vw_productdetailsbycustomer` DISABLE KEYS */;
/*!40000 ALTER TABLE `vw_productdetailsbycustomer` ENABLE KEYS */;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;





