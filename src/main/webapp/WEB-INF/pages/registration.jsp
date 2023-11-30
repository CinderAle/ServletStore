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
    <h1><fmt:message key="registration_form"/></h1>
    <form action="controller" method="post">
        <input type="hidden" name="task" value="do_register">
        <label><fmt:message key="user_name"/></label>
        <input type="text" name="name" placeholder='<fmt:message key="user_name"/>' required>
        <label><fmt:message key="user_email"/></label>
        <input type="email" name="email" placeholder='johndoe@mail.com' required>
        <label><fmt:message key="user_password"/></label>
        <input type="password" placeholder="password" name="password" required>
        <label><fmt:message key="user_repassword"/></label>
        <input type="password" placeholder="password" name="repassword" required>
        <input type="submit"  value='<fmt:message key="register_btn"/>'>
        <div>${error}</div>
    </form>
    <div class="controlBlock">
        <p class="registerLink">
            <fmt:message key="account_exists"/>?
            <a href="<c:url value="controller?page=login.jsp"/>"><fmt:message key="login_link"/>!</a>
        </p>
        <form class="changeLanguageForm" action="controller" method="post">
            <input type="hidden" name="task" value="change_language">
            <select name="language" onchange="this.form.submit()">
                <option value="en" ${sessionScope.lang == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${sessionScope.lang == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
    </div>
</div>
</body>
</html>
