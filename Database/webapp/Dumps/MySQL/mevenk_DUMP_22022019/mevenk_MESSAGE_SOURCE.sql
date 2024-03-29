-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: VENKATESH-NUC    Database: mevenk
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `MESSAGE_SOURCE`
--

DROP TABLE IF EXISTS `MESSAGE_SOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MESSAGE_SOURCE` (
  `MESSAGE_KEY` int(11) NOT NULL,
  `MESSAGE_ID` int(11) NOT NULL,
  `LOCALE_ID` int(11) NOT NULL,
  `MESSAGE` varchar(500) NOT NULL,
  PRIMARY KEY (`MESSAGE_KEY`),
  KEY `LOCALE_ID_FK_idx` (`LOCALE_ID`),
  CONSTRAINT `LOCALE_ID_FK` FOREIGN KEY (`LOCALE_ID`) REFERENCES `USER_LOCALE` (`LOCALE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MESSAGE_SOURCE`
--

LOCK TABLES `MESSAGE_SOURCE` WRITE;
/*!40000 ALTER TABLE `MESSAGE_SOURCE` DISABLE KEYS */;
INSERT INTO `MESSAGE_SOURCE` VALUES (10001,101,0,'NULL not allowed'),(10002,101,1,'शून्य की अनुमति नहीं है'),(10003,101,2,'nulo no permitido'),(10004,102,0,'hidden NULL not allowed'),(10005,102,1,'छुपा नल की अनुमति नहीं है'),(10006,102,2,'oculta nula no permitida');
/*!40000 ALTER TABLE `MESSAGE_SOURCE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 22:37:54
