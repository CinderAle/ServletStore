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
    <h1><fmt:message key="login_form"/></h1>
    <form action="controller" method="post">
        <input type="hidden" name="task" value="do_login">
        <label><fmt:message key="user_email"/></label>
        <input type="email" name="email" placeholder="johndoe@mail.com" required>
        <label><fmt:message key="user_password"/></label>
        <input type="password" placeholder="Password"  name="password" required>
        <input type="submit" value='<fmt:message key="login_btn"/>'>
        <div>${error}</div>
    </form>
    <div class="controlBlock">
        <p class="registerLink">
            <fmt:message key="no_account_quest"/>?
            <a href="<c:url value="controller?page=registration.jsp"/>"><fmt:message key="register_link"/>!</a>
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
