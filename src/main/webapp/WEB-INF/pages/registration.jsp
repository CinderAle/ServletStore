<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["error"]}' var="error"/>
<c:set value='${requestScope["message"]}' var="message"/>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/registerStyle.css">
</head>
<body>
<div class="loginBox reg">
    <h1>Registration form</h1>
    <form action="controller" method="post">
        <input type="hidden" name="task" value="do_register">
        <label>Name</label>
        <input type="text" name="name" placeholder="Name" required>
        <label>E-mail</label>
        <input type="email" name="email" placeholder="E-mail" required>
        <label>Password</label>
        <input type="password" placeholder="Password" name="password" required>
        <label>Repeat Password</label>
        <input type="password" placeholder="Password" name="repassword" required>
        <input type="submit"  value="Register">
        <div>${error}</div>
    </form>
    <p class="registerLink">Already have an account? <a href="<c:url value="controller?page=login.jsp"/>">Sign in!</a></p>
</div>
</body>
</html>
