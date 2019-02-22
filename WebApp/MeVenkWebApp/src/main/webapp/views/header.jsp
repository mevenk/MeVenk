<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mevenk" uri="/WEB-INF/taglibs/MeVenk.tld"%>

<!DOCTYPE html>
<html>
<head>
</head>


<div id="headerLeftSection">

	<div id="dateDisplay">
		<h4>${mevenk:printDate() }</h4>
	</div>

	<br>

	<div id="runningClockDisplay">
		<span id="runningClock"></span>
	</div>

</div>

<div id="headerRightSection">

	<div id="userInformationDisplayHeader">

		<span>${userSession.uid }
			[${userSession.userIdentificationNumber }]</span> <br> <span>${userSession.fullName }</span>

	</div>

	<br>

	<div id="userActions">

		<a id="logout" href="./logoutUser.mevenk">Logout</a>

	</div>
</div>


</html>