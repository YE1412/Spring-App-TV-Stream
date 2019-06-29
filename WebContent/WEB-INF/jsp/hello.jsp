<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><spring:message code="helloPage.title" /></title></head>
  <body>
    <h1><spring:message code="helloPage.headline" /></h1>
    <spring:message code="language.text" text="Languge" /> : <a href="?language=en">English</a>|<a href="?language=fr_FR">Français</a>
    <p><spring:message code="helloPage.text" /> <c:out value="${now}" default="None" /></p>
   </body>
</html>