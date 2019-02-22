<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="mevenk" uri="/WEB-INF/taglibs/MeVenk.tld"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>


	<div id="footerLeftSection">

		<div id="footerDateDisplay">
			<h5>Footer: ${mevenk:printDate() }</h5>
		</div>

	</div>

	<div id="footerRightSection">

		<div id="userInformationDisplayFooter">

			<span>${user.uid }</span>

		</div>
	</div>


</body>
</html>