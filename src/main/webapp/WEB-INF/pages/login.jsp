<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["loginFlag"]}' var="loginFlag"/>
<c:if test="${not empty loginFlag}">
    <c:redirect url="/controller?task=get_products"/>
</c:if>
<c:set value='${requestScope["error"]}' var="error"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/loginStyle.css">
    <title>Log in</title>
</head>
<body>
<div class="loginBox log">
    <h1>Log in form</h1>
    <form action="controller" method="post">
        <input type="hidden" name="task" value="do_login">
        <label>E-mail</label>
        <input type="email" name="email" placeholder="E-mail" required>
        <label>Password</label>
        <input type="password" placeholder="Password"  name="password" required>
        <input type="submit" value="Log in">
        <div>${error}</div>
    </form>
    <p class="registerLink">Don't have an account? <a href="<c:url value="controller?page=registration.jsp"/>">Register!</a></p>
</div>
</body>
</html>
