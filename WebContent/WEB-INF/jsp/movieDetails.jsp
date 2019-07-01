<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
	<title><spring:message code="movieDetails.title" /></title>
</head>
<h2><spring:message code="movieDetails.headline" /></h2>
<table>
    <tr>
        <td><spring:message code="movieDetails.tableHead1" /></td>
        <td>${formDataWithFile.file.originalFilename}</td>
    </tr>
    <tr>
        <td><spring:message code="movieDetails.tableHead2" /></td>
        <td>${formDataWithFile.dest_name}</td>
    </tr>
    <tr>
        <td><spring:message code="movieDetails.tableHead3" /></td>
        <td>${formDataWithFile.file.contentType}</td>
    </tr>
</table>
</html>