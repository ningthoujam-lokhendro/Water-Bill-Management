-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `bill_status`
--

DROP TABLE IF EXISTS `bill_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `connection_type`
--

DROP TABLE IF EXISTS `connection_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connection_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TYPE_IDX` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `customer_id` varchar(10) DEFAULT NULL,
  `first_name` varchar(20) NOT NULL,
  `phone_landline` varchar(15) DEFAULT NULL,
  `last_name` varchar(20) NOT NULL,
  `phone_mobile` varchar(15) DEFAULT NULL,
  `patta_number` varchar(255) DEFAULT NULL,
  `ward_number` int(11) NOT NULL,
  `ref_connection_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PATTA_NUMBER_IDX` (`patta_number`),
  UNIQUE KEY `CUSTOMER_ID_IDX` (`customer_id`),
  KEY `FK_CONNECTION_TYPE` (`ref_connection_type`),
  CONSTRAINT `FK_CONNECTION_TYPE` FOREIGN KEY (`ref_connection_type`) REFERENCES `connection_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meter`
--

DROP TABLE IF EXISTS `meter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meter_id` varchar(255) DEFAULT NULL,
  `ref_customer` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CUSTOMER` (`ref_customer`),
  CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`ref_customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meter_reading`
--

DROP TABLE IF EXISTS `meter_reading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meter_reading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reading_date` date NOT NULL,
  `value` int(11) NOT NULL,
  `ref_meter` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_METER` (`ref_meter`),
  CONSTRAINT `FK_METER` FOREIGN KEY (`ref_meter`) REFERENCES `meter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_payment` datetime NOT NULL,
  `paid_amount` int(11) NOT NULL,
  `ref_payement_status_code` bigint(20) NOT NULL,
  `ref_payment_type` bigint(20) NOT NULL,
  `ref_water_bill` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PAYEMENT_STATUS_CODE` (`ref_payement_status_code`),
  KEY `FK_PAYMENT_TYPE` (`ref_payment_type`),
  KEY `FK_WATER_BILL` (`ref_water_bill`),
  CONSTRAINT `FK_WATER_BILL` FOREIGN KEY (`ref_water_bill`) REFERENCES `water_bill` (`id`),
  CONSTRAINT `FK_PAYEMENT_STATUS_CODE` FOREIGN KEY (`ref_payement_status_code`) REFERENCES `payment_status_code` (`id`),
  CONSTRAINT `FK_PAYMENT_TYPE` FOREIGN KEY (`ref_payment_type`) REFERENCES `payment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_status_code`
--

DROP TABLE IF EXISTS `payment_status_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_status_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `water_bill`
--

DROP TABLE IF EXISTS `water_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `water_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_due` int(11) NOT NULL,
  `due_date` date NOT NULL,
  `period_from` date NOT NULL,
  `period_to` date NOT NULL,
  `ref_meter_reading` bigint(20) NOT NULL,
  `ref_bill_status` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_METER_READING` (`ref_meter_reading`),
  KEY `FK_BILL_STATUS` (`ref_bill_status`),
  CONSTRAINT `FK_BILL_STATUS` FOREIGN KEY (`ref_bill_status`) REFERENCES `bill_status` (`id`),
  CONSTRAINT `FK_METER_READING` FOREIGN KEY (`ref_meter_reading`) REFERENCES `meter_reading` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-07 16:56:08
