<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mevenk" uri="/WEB-INF/taglibs/MeVenk.tld"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/login.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/login.js"></script>
<meta charset="ISO-8859-1">
</head>
<body>

	<form:form id="loginUser" action="./loginUser.mevenk"
		modelAttribute="userLogin">

		<table id="tableLoginUserForm">

			<tbody>

				<tr>
					<td>UID:</td>
					<td><form:input path="uid" /></td>
				</tr>
				<tr>
					<td />
					<td><form:errors cssClass="loginErrorMessage" path="uid" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td />
					<td><form:errors cssClass="loginErrorMessage" path="password" /></td>
				</tr>
			</tbody>

			<tfoot>
				<tr>
					<td><input type="submit" value="Login" /></td>
					<td>${loginStatus }</td>
				</tr>

			</tfoot>

		</table>

	</form:form>

</body>
</html>