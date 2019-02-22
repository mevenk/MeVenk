CREATE DATABASE  IF NOT EXISTS `mevenk` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `mevenk`;
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
-- Table structure for table `APPLICATION_EXCEPTION`
--

DROP TABLE IF EXISTS `APPLICATION_EXCEPTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `APPLICATION_EXCEPTION` (
  `EXCEPTION_ID` int(11) NOT NULL,
  `EXCEPTION_CLASS` varchar(500) DEFAULT NULL,
  `STACK_TRACE` longtext,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UID` varchar(7) DEFAULT NULL,
  `APPLICATION_CORRELAION_ID` tinytext,
  `HTTP_SESSION_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EXCEPTION_ID`),
  UNIQUE KEY `idAPPLICATION_EXCEPTION_ID_UNIQUE` (`EXCEPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Temporary view structure for view `APPLICATION_EXCEPTION_VW`
--

DROP TABLE IF EXISTS `APPLICATION_EXCEPTION_VW`;
/*!50001 DROP VIEW IF EXISTS `APPLICATION_EXCEPTION_VW`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `APPLICATION_EXCEPTION_VW` AS SELECT 
 1 AS `EXCP_ID`,
 1 AS `EXCP_CLAS_NAME`,
 1 AS `APP_CORRELATION_ID`,
 1 AS `STACK_TRACE`,
 1 AS `OCCURED_MIN_AGO`,
 1 AS `UID`,
 1 AS `CREATED`*/;
SET character_set_client = @saved_cs_client;

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
  CONSTRAINT `LOCALE_ID_FK` FOREIGN KEY (`LOCALE_ID`) REFERENCES `USER_LOCALE` (`locale_id`)
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

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SEQUENCE` (
  `SEQUENCE_NAME` varchar(45) NOT NULL,
  `SEQUENCE_VALUE` int(15) NOT NULL,
  PRIMARY KEY (`SEQUENCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('APPLICATION_EXCEPTION_ID',1001),('USER_IDENTIFICATION_NUMBER',1000000001);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `USER` (
  `USER_IDENTIFICATION_NUMBER` int(25) NOT NULL DEFAULT '-1',
  `UID` varchar(7) DEFAULT NULL,
  `LOCALE` int(11) NOT NULL,
  `ROLE` int(11) NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_NAME` varchar(45) DEFAULT '',
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `FULL_NAME` varchar(150) DEFAULT NULL,
  `MOBILE_NO` int(25) DEFAULT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `PROFILE` blob,
  `RESUME` blob,
  `CREATED_ON` datetime NOT NULL,
  `MODIFIED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_IDENTIFICATION_NUMBER`),
  UNIQUE KEY `IDENTIFICATION_NO_UNIQUE` (`USER_IDENTIFICATION_NUMBER`),
  UNIQUE KEY `UID_UNIQUE` (`UID`),
  KEY `ROLE-ROLE_ID_idx` (`ROLE`),
  KEY `LOCALE-LOCALE_ID_idx` (`LOCALE`),
  CONSTRAINT `LOCALE-LOCALE_ID` FOREIGN KEY (`LOCALE`) REFERENCES `USER_LOCALE` (`locale_id`),
  CONSTRAINT `ROLE-ROLE_ID` FOREIGN KEY (`ROLE`) REFERENCES `USER_ROLE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1000000013,'VK00013',1,100,'VENKATESH','','KOLISETTY','KOLISETTY, VENKATESH',NULL,NULL,'1992-04-25',NULL,NULL,'2018-11-20 22:23:22',NULL),(1000000014,'VV00014',0,100,'VENKY','','VENKY','VENKY, VENKY',NULL,NULL,'1992-04-25',NULL,NULL,'2018-11-21 06:39:37',NULL),(1000000015,'MV00015',2,100,'MEVENK','','VENKY','VENKY, MEVENK',NULL,NULL,'1992-04-25',NULL,NULL,'2018-11-21 06:39:37',NULL);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`mevenk_webapp_application`@`%`*/ /*!50003 TRIGGER `USER_BEFORE_INSERT` BEFORE INSERT ON `USER` FOR EACH ROW BEGIN
	
    SET NEW.CREATED_ON = SYSDATE();
    
    SET NEW.FIRST_NAME = TRIM(UPPER(NEW.FIRST_NAME));
    
    SET NEW.MIDDLE_NAME = TRIM(UPPER(NEW.MIDDLE_NAME));
    
    SET NEW.LAST_NAME = TRIM(UPPER(NEW.LAST_NAME));
    
    SET NEW.FULL_NAME = GENERATE_USER_FULL_NAME(NEW.FIRST_NAME, NEW.MIDDLE_NAME, NEW.LAST_NAME);
    
    IF(NEW.USER_IDENTIFICATION_NUMBER = -1) THEN
        SET NEW.USER_IDENTIFICATION_NUMBER = GENERATE_USER_IDENTIFICATION_NUMBER();
	END IF;
    
    SET NEW.UID = GENERATE_USER_UID(NEW.FIRST_NAME, NEW.LAST_NAME, NEW.USER_IDENTIFICATION_NUMBER);
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `USER_LOCALE`
--

DROP TABLE IF EXISTS `USER_LOCALE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `USER_LOCALE` (
  `LOCALE_ID` int(11) NOT NULL,
  `LOCALE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`LOCALE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_LOCALE`
--

LOCK TABLES `USER_LOCALE` WRITE;
/*!40000 ALTER TABLE `USER_LOCALE` DISABLE KEYS */;
INSERT INTO `USER_LOCALE` VALUES (0,'ENGLISH'),(1,'HINDI'),(2,'SPANISH');
/*!40000 ALTER TABLE `USER_LOCALE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_PASSWORD`
--

DROP TABLE IF EXISTS `USER_PASSWORD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `USER_PASSWORD` (
  `UID` varchar(7) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  PRIMARY KEY (`UID`),
  KEY `UID-UID_idx` (`UID`),
  CONSTRAINT `UID-UID` FOREIGN KEY (`UID`) REFERENCES `USER` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_PASSWORD`
--

LOCK TABLES `USER_PASSWORD` WRITE;
/*!40000 ALTER TABLE `USER_PASSWORD` DISABLE KEYS */;
INSERT INTO `USER_PASSWORD` VALUES ('MV00015','MV00015'),('VK00013','VK00013'),('VV00014','VV00014');
/*!40000 ALTER TABLE `USER_PASSWORD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ROLE`
--

DROP TABLE IF EXISTS `USER_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `USER_ROLE` (
  `ROLE_ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_ID_UNIQUE` (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME_UNIQUE` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ROLE`
--

LOCK TABLES `USER_ROLE` WRITE;
/*!40000 ALTER TABLE `USER_ROLE` DISABLE KEYS */;
INSERT INTO `USER_ROLE` VALUES (100,'WEBAPP_ADMIN');
/*!40000 ALTER TABLE `USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `USER_VW`
--

DROP TABLE IF EXISTS `USER_VW`;
/*!50001 DROP VIEW IF EXISTS `USER_VW`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `USER_VW` AS SELECT 
 1 AS `USER_IDENTIFICATION_NUMBER`,
 1 AS `UID`,
 1 AS `LOCALE`,
 1 AS `ROLE`,
 1 AS `FULL_NAME`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'mevenk'
--




--
-- Prevent errors during Functions creation
--

SET GLOBAL log_bin_trust_function_creators = 1;

--
-- Prevent errors during Functions creation
--


--
-- Dumping routines for database 'mevenk'
--
/*!50003 DROP FUNCTION IF EXISTS `GENERATE_USER_FULL_NAME` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`mevenk_webapp_application`@`%` FUNCTION `GENERATE_USER_FULL_NAME`(`FIRST_NAME` varchar(45), `MIDDLE_NAME` varchar(45), `LAST_NAME` varchar(45)) RETURNS varchar(150) CHARSET utf8mb4
BEGIN

RETURN UPPER(TRIM(CONCAT(LAST_NAME, ', ', FIRST_NAME, ' ', MIDDLE_NAME)));

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `GENERATE_USER_IDENTIFICATION_NUMBER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`mevenk_webapp_application`@`%` FUNCTION `GENERATE_USER_IDENTIFICATION_NUMBER`() RETURNS int(15)
BEGIN
	
    SET @UIN_NEW = (SELECT SEQUENCE_VALUE FROM SEQUENCE WHERE SEQUENCE_NAME = 'USER_IDENTIFICATION_NUMBER');
    UPDATE SEQUENCE SET SEQUENCE_VALUE = @UIN_NEW + 1 WHERE SEQUENCE_NAME = 'USER_IDENTIFICATION_NUMBER';
    
	RETURN @UIN_NEW;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `GENERATE_USER_UID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`mevenk_webapp_application`@`%` FUNCTION `GENERATE_USER_UID`(`FIRST_NAME` VARCHAR(45), `LAST_NAME` VARCHAR(45), `USER_IDENTIFICATION_NUMBER` VARCHAR(25)) RETURNS varchar(7) CHARSET utf8mb4
BEGIN

RETURN TRIM(CONCAT(SUBSTR(FIRST_NAME, 1, 1), SUBSTR(LAST_NAME, 1, 1), SUBSTR(USER_IDENTIFICATION_NUMBER, -5)));

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `APPLICATION_EXCEPTION_VW`
--

/*!50001 DROP VIEW IF EXISTS `APPLICATION_EXCEPTION_VW`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mevenk_webapp_application`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `APPLICATION_EXCEPTION_VW` AS select `APPLICATION_EXCEPTION`.`EXCEPTION_ID` AS `EXCP_ID`,substring_index(`APPLICATION_EXCEPTION`.`EXCEPTION_CLASS`,'.',-(1)) AS `EXCP_CLAS_NAME`,`APPLICATION_EXCEPTION`.`APPLICATION_CORRELAION_ID` AS `APP_CORRELATION_ID`,`APPLICATION_EXCEPTION`.`STACK_TRACE` AS `STACK_TRACE`,timestampdiff(MINUTE,`APPLICATION_EXCEPTION`.`CREATED_DATE`,now()) AS `OCCURED_MIN_AGO`,`APPLICATION_EXCEPTION`.`UID` AS `UID`,date_format(`APPLICATION_EXCEPTION`.`CREATED_DATE`,'%H:%i:%s  [%d-%M-%Y]') AS `CREATED` from `APPLICATION_EXCEPTION` order by `APPLICATION_EXCEPTION`.`EXCEPTION_ID` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `USER_VW`
--

/*!50001 DROP VIEW IF EXISTS `USER_VW`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mevenk_webapp_application`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `USER_VW` AS select `USER`.`USER_IDENTIFICATION_NUMBER` AS `USER_IDENTIFICATION_NUMBER`,`USER`.`UID` AS `UID`,`USER`.`LOCALE` AS `LOCALE`,`USER`.`ROLE` AS `ROLE`,`USER`.`FULL_NAME` AS `FULL_NAME` from `USER` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 22:19:19
