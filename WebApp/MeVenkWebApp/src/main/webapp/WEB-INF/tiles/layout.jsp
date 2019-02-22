<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mevenk" uri="/WEB-INF/taglibs/MeVenk.tld"%>

<c:set var="userSession" scope="request"
	value="${sessionScope.SESSION_ATTRIBUTE_NAME_USER}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/applicationMain.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/core/jquery/jquery-ui/jquery-ui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/core/jquery/jquery-ui/jquery-ui.structure.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/core/jquery/jquery-ui/jquery-ui.theme.css">

<!-- Header -->

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/core/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/core/jquery/jquery-ui/jquery-ui.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/header.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/header.js"></script>


<!-- Header END-->

<!-- Footer -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/footer.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/footer.js"></script>

<!-- Footer END-->

</head>
<body>
	<div id="headerApplication">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="bodyApplication">
		<tiles:insertAttribute name="body" />
	</div>
	<div id="footerApplication">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>