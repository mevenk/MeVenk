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

	<mevenk:comment commentId="simpleComment" simpleComment="A Simple Comment" />

	<mevenk:comment commentId="multiLineComment">
		Unparsable comment
	</mevenk:comment>

	<h1>${welcomeMessage }-${mevenk:printDate() }</h1>
	<h5>DB Time: ${databaseTime }</h5>
	<h5>DB Date Time Formatted: ${databaseTimeFormatted }</h5>

	<%for(int i=0;i<=10000;i++){%>

	<h5>DB Time: ${databaseTime }</h5>
	<%} %>

</body>
</html>
