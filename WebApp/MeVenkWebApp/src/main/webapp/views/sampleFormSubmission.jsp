<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>

	<form:form action="./sampleFormSubmit.mevenk" method="POST"
		modelAttribute="sampleForm">

		<form:errors path="hiddenString" />

		<input type="submit" value="Submit" />

	</form:form>

</body>
</html>