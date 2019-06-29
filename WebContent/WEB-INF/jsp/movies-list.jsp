<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/movies/edit" />

<html>
<head>
<title><spring:message code="moviePage.title" /></title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
    <div class="container">
        <h1><spring:message code="moviePage.headline" /></h1>
        <spring:message code="language.text" /> : <a href="?language=en">English</a>|<a href="?language=fr_FR">Français</a>
        <table class="table table-hover">
            <c:forEach items="${movies}" var="m">
                <tr>
                    <td><a href="${edit}?id=${m.id}">
                        <c:out value="${m.name}" />
                    </a></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}"><spring:message code="moviePage.link" /></a>
        </p>
    </div>
</body>
</html>