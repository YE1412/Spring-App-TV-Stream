<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login"  value="/actions/user/login" />
<c:url var="logout" value="/actions/user/logout" />
<c:url var="show"   value="/actions/user/show" />
<c:url var="register"   value="/actions/user/register" />

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
<title><spring:message code="userPage.title" /></title>
</head>
<body>
	<div class="container">
    <h1><spring:message code="userPage.headline" /></h1>
	<spring:message code="language.text" /> : <a href="?language=en">English</a>|<a href="?language=fr_FR">Français</a>
    <p>
    <spring:message code="userPage.labelName" /><c:out value="${user.nom}" default="no name"/> | 
    <a href="${show}"><spring:message code="userPage.link1" /></a> | <a href="${login}"><spring:message code="userPage.link2" /></a> |
    <a href="${logout}"><spring:message code="userPage.link3" /></a> | <a href="${register}"><spring:message code="userPage.link4" /></a>
    </p>
    </div>
</body>
</html>