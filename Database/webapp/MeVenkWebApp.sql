
SELECT DATABASE();
SELECT DATABASE(), USER(), VERSION();

SELECT CURRENT_USER();

SELECT sysdate();

SELECT SUBSTR('VENKATESH', 1 ,1);

SELECT SUBSTRING_INDEX('org.springframework.web.servlet.NoHandlerFoundException', '.', -1);


SELECT DATE_FORMAT(SYSDATE(), '%d %m %Y  %H:%i:%s') as DATE;

SELECT * FROM MESSAGE_SOURCE;




select 'select * into mevenk.USER_LOCALE from webapp.USER_LOCALE' + USER_LOCALE from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'mevenk';

ALTER table webapp.MESSAGE_SOURCE rename mevenk.MESSAGE_SOURCE;



/*
--------------------------------------------------------------------------------------------------------
*/


SELECT * FROM APPLICATION_EXCEPTION ORDER BY EXCEPTION_ID DESC;


SELECT * FROM SEQUENCE;

SELECT * FROM USER;

SELECT * FROM APPLICATION_EXCEPTION_VW;

SELECT * FROM USER_VW;

SELECT * FROM MESSAGE_SOURCE;

SELECT * FROM USER_PASSWORD;

/*
--------------------------------------------------------------------------------------------------------
*/

SET GLOBAL log_bin_trust_function_creators = 1;


SELECT EXCEPTION_ID AS EXCP_ID, 
SUBSTR(EXCEPTION_CLASS, SUBSTRING_INDEX(EXCEPTION_CLASS, '.',0)) AS EXCP_CLAS_NAME, 
STACK_TRACE, 
TIMESTAMPDIFF(MINUTE, CREATED_DATE, NOW()) AS OCCURED_MIN_AGO, 
UID, 
DATE_FORMAT(CREATED_DATE, '%H:%i:%s  [%d-%M-%Y]') AS CREATED 
FROM  APPLICATION_EXCEPTION 
ORDER BY EXCEPTION_ID DESC;








/*
-------------------------------------------------------------------------------------------------------
*/


