<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table {
	border: 3px solid black;
}

table tr {
	border: 2px solid black;
}

table tr td {
	border: 1px solid black;
	width: 250px;
}

.errorMessage {
	background: yellow;
	color: red;
	white-space: normal;
}
</style>
</head>
<body>

	<form:form action="./sampleFormSubmit.mevenk" method="POST"
		modelAttribute="sampleForm">

		<table>

			<tbody>

				<tr>

					<td><form:input path="number" /></td>

					<td><form:input path="name" /></td>

					<td><form:radiobuttons items="${radioButtonItems }"
							path="radioButton" /></td>

					<td><form:checkbox path="checkBoxBoolean" /></td>

					<td><form:hidden path="hiddenNumber" /> <form:hidden
							path="hiddenString" /> <form:hidden path="hiddenBoolean" /></td>


				</tr>

				<tr>

					<td><form:errors cssClass="errorMessage" path="number" /></td>

					<td><form:errors cssClass="errorMessage" path="name" /></td>

					<td><form:errors cssClass="errorMessage" path="radioButton" /></td>

					<td><form:errors path="checkBoxBoolean" /></td>

					<td><form:errors cssClass="errorMessage" path="hiddenNumber" />
						<br /> <form:errors cssClass="errorMessage" path="hiddenString" />
						<br /> <form:errors cssClass="errorMessage" path="hiddenBoolean" /></td>

				</tr>


			</tbody>

		</table>



		<input type="submit" value="Submit" />

	</form:form>

</body>
</html>