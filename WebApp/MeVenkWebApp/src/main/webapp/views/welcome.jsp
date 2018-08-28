<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="mevenk" uri="/WEB-INF/taglibs/MeVenk.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MeVenk WebApp - Welcome</title>
</head>
<body>
	<h1>${welcomeMessage } - ${mevenk:printDate() }</h1>
</body>
</html>