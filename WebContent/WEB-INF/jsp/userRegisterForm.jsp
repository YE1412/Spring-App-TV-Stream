<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
<title><spring:message code="userRegisterFormPage.title" /></title>
</head>

<body>

    <div class="container">
        <h1><spring:message code="userRegisterFormPage.headline" /></h1>

        <form:form method="POST" modelAttribute="user" enctype="multipart/form-data">

            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="nom"><spring:message code="userRegisterFormPage.labelName" /></label>
                <form:input class="form-control" path="nom" />
                <form:errors path="nom" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
                <label for="pass"><spring:message code="userRegisterFormPage.labelPassword" /></label>
                <form:input class="form-control" path="pass" type="password" />
                <form:errors path="pass" cssClass="alert alert-warning"
                    element="div" />
            </div>
            
            <div class="form-group">
                <button type="submit" class="btn btn-info"><spring:message code="userRegisterFormPage.submitButtonText" /></button>
            </div>
        </form:form>
    </div>

</body>
</html>