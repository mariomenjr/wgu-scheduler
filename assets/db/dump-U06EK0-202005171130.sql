-- MySQL dump 10.16  Distrib 10.1.44-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 3.227.166.251    Database: U06EK0
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` int(10) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `address2` varchar(50) NOT NULL,
  `cityId` int(10) NOT NULL,
  `postalCode` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `cityId` (`cityId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'90 Java Dr','Building 30',1,'92223','1451236522','2020-04-14 23:05:18','mariomenjr','2020-04-15 06:06:15','mariomenjr'),(2,'132','3213',2,'3213','13','2020-05-06 20:19:15','mariomenjr','2020-05-06 20:19:15','mariomenjr'),(3,'8080 E. Crystal','',3,'92831','1234567890','2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(4,'8080 E. Crystal','',4,'92831','1234567890','2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(5,'123456','478913',5,'465464','654654','2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(6,'123456','478913',6,'465464','654654','2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(7,'123','132',7,'132','132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(8,'123','132',8,'132','132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(9,'123','132',9,'132','132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(10,'456 E Wolshi','J',10,'92365','1213233222','2020-05-11 21:23:49','mariomenjr','2020-05-11 21:23:49','mariomenjr'),(11,'321 Deer Run','Venison Ln',11,'45896','2563698','2020-05-11 23:01:56','test','2020-05-11 23:01:56','test'),(12,'646','46',12,'4','64','2020-05-11 23:14:59','mariomenjr','2020-05-11 23:14:59','mariomenjr'),(13,'888 BigMac Drive','French Fry Cove',13,'96325','7458963','2020-05-13 12:34:46','test','2020-05-13 12:34:46','test'),(14,' 33 Oak St','n/a',14,'41258','1259632','2020-05-13 12:36:28','test','2020-05-13 12:36:28','test');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointmentId` int(10) NOT NULL AUTO_INCREMENT,
  `customerId` int(10) NOT NULL,
  `userId` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `location` text NOT NULL,
  `contact` text NOT NULL,
  `type` text NOT NULL,
  `url` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`appointmentId`),
  KEY `userId` (`userId`),
  KEY `appointment_ibfk_1` (`customerId`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (75,1,1,'Overlapped 1','Overlapped 1','Overlapped 1','Overlapped 1','Overlapped 1','Overlapped 1','2020-05-16 21:00:47','2020-05-16 21:30:47','2020-05-17 03:57:47','mariomenjr','2020-05-17 03:57:47','mariomenjr'),(76,2,2,'Overlapped 2','Overlapped 2','Overlapped 2','Overlapped 2','Overlapped 2','Overlapped 2','2020-05-20 18:00:20','2020-05-20 19:00:20','2020-05-17 03:57:20','test','2020-05-17 03:57:20','test'),(77,21,2,'Appt 1','Appt 1','Home office','Florence','Online','www','2020-05-20 14:00:19','2020-05-20 15:00:19','2020-05-17 04:17:19','test','2020-05-17 04:17:19','test'),(78,21,2,'Review II','Review II','Home office','Mark','Phone','www','2020-05-20 15:00:09','2020-05-20 16:00:09','2020-05-17 04:18:09','test','2020-05-17 04:18:09','test');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `cityId` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `countryId` int(10) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`cityId`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Montain View',1,'2020-04-14 23:05:44','mariomenjr','2020-04-15 06:05:55','mariomenjr'),(2,'1321',4,'2020-05-06 20:19:15','mariomenjr','2020-05-06 20:19:15','mariomenjr'),(3,'Anaheim',5,'2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(4,'Anaheim',6,'2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(5,'16546',7,'2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(6,'16546',8,'2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(7,'132',9,'2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(8,'132',10,'2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(9,'132',11,'2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(10,'Fullerto',12,'2020-05-11 21:23:49','mariomenjr','2020-05-11 21:23:49','mariomenjr'),(11,'Tulsa',13,'2020-05-11 23:01:56','test','2020-05-11 23:01:56','test'),(12,'46',14,'2020-05-11 23:14:59','mariomenjr','2020-05-11 23:14:59','mariomenjr'),(13,'San Bernadino',15,'2020-05-13 12:34:46','test','2020-05-13 12:34:46','test'),(14,'Atlanta',16,'2020-05-13 12:36:28','test','2020-05-13 12:36:28','test');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryId` int(10) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'United States','2020-04-14 23:04:26','mariomenjr','2020-04-15 06:04:27',''),(2,'321','2020-05-06 20:17:01','mariomenjr','2020-05-06 20:17:01','mariomenjr'),(3,'321321','2020-05-06 20:18:16','mariomenjr','2020-05-06 20:18:16','mariomenjr'),(4,'1321321','2020-05-06 20:19:15','mariomenjr','2020-05-06 20:19:15','mariomenjr'),(5,'United States','2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(6,'United States','2020-05-06 20:27:27','mariomenjr','2020-05-06 20:27:27','mariomenjr'),(7,'54654654','2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(8,'54654654','2020-05-06 20:41:15','mariomenjr','2020-05-06 20:41:15','mariomenjr'),(9,'132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(10,'132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(11,'132','2020-05-06 22:06:33','mariomenjr','2020-05-06 22:06:33','mariomenjr'),(12,'US','2020-05-11 21:23:49','mariomenjr','2020-05-11 21:23:49','mariomenjr'),(13,'USA','2020-05-11 23:01:56','test','2020-05-11 23:01:56','test'),(14,'64','2020-05-11 23:14:59','mariomenjr','2020-05-11 23:14:59','mariomenjr'),(15,'USA','2020-05-13 12:34:46','test','2020-05-13 12:34:46','test'),(16,'USA','2020-05-13 12:36:28','test','2020-05-13 12:36:28','test');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` int(10) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(45) NOT NULL,
  `addressId` int(10) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`customerId`),
  KEY `addressId` (`addressId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Software Pro',1,1,'2020-04-14 23:06:48','mariomenjr','2020-04-15 06:06:54','mariomenjr'),(2,'Database Inc',1,1,'2020-04-18 00:26:08','mariomenjr','2020-04-18 00:26:15','mariomenjr'),(7,'Software Inc',1,1,'2020-04-18 00:28:44','mariomenjr','2020-04-18 00:28:40','mariomenjr'),(19,'John Doe Adeer',11,1,'2020-05-11 23:01:56','test','2020-05-13 12:35:31','test'),(21,'Ronald McDonald',13,1,'2020-05-13 12:34:46','test','2020-05-13 12:34:46','test');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `active` tinyint(4) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mariomenjr','password',1,'2020-04-11 23:10:00','root','2020-05-13 03:05:58','root'),(2,'test','test',1,'2020-05-12 21:37:01','root','2020-05-13 03:05:58','root');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'U06EK0'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-17 11:30:41
