<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
<title><spring:message code="movieFormPage.title" /></title>
</head>

<body>

    <div class="container">
        <h1><spring:message code="movieFormPage.headline" /></h1>

        <form:form method="POST" modelAttribute="movieWithDatas" enctype="multipart/form-data">
			<c:out value="${movieWithDatas.dest_name}">${movieWithDatas}</c:out>
            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="dest_name"><spring:message code="movieFormPage.labelName" /></label>
                <form:input class="form-control" path="dest_name" />
                <form:errors path="dest_name" cssClass="alert alert-warning"
                    element="div" />
            </div>
            
            <div class="form-group">
                <label for="file"><spring:message code="movieFormPage.labelFile" /></label>
                <form:input class="form-control" type="file" path="file" />
                <form:errors path="file" cssClass="alert alert-warning"
                    element="div" />
            </div>
            
            <div class="form-group">
                <button type="submit" class="btn btn-info"><spring:message code="movieFormPage.submitButtonText" /></button>
            </div>
        </form:form>
    </div>

</body>
</html>