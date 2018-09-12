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

	<mevenk:comment id="simpleComment" simpleComment="A Simple Comment" />

	<mevenk:comment id="multiLineComment">
		Unparsable comment
	</mevenk:comment>

	<h1>${welcomeMessage }-${mevenk:printDate() }</h1>
	<h5>DB Time: ${databaseTime }</h5>
</body>
</html>