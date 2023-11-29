<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["loginFlag"]}' var="loginFlag"/>
<c:if test="${not empty loginFlag}">
    <c:redirect url="/controller?task=get_products"/>
</c:if>
<c:set value='${requestScope["error"]}' var="error"/>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <div>${error}</div>
</body>
</html>
