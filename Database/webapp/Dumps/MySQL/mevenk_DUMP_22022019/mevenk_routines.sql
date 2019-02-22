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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 22:38:03
