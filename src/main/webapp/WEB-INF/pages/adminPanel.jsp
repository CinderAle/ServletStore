<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${sessionScope["user"]}' var="user"/>
<c:set value='${requestScope["error"]}' var="error"/>
<c:if test="${empty user}">
    <c:redirect url="/controller?page=login.jsp"/>
</c:if>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/headerStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/panelStyle.css">
</head>
<body>
<%@ include file="../parts/header.jsp" %>
<div class="displayContainer">
    <div>${error}</div>
    <div class="newProductBlock">
        <form method="post" action="controller">
            <input type="hidden" name="task" value="add_product">
            <div class="parameterBlock">
                <label><fmt:message key="product_image_name"/></label>
                <input type="text" name="newProductImage" required>
            </div>
            <div class="parameterBlock">
                <label><fmt:message key="product_name"/></label>
                <input type="text" name="newProductName" required>
            </div>
            <div class="parameterBlock">
            <label><fmt:message key="product_price"/></label>
            <input type="text" name="newProductPrice" required>
            </div>
            <button type="submit"><fmt:message key="add_product"/></button>
        </form>
    </div>
    <div class="userList">
        <c:set value='${requestScope["usersList"]}' var="usersList"/>
        <c:forEach var="user" items="${usersList}">
            <c:if test="${user.getRole() == 2}">
                <div class="userBlock">
                    <div class="userBasicInfo">
                        <h2 class="userName">${user.getName()}</h2>
                        <h2 class="userEmail">${user.getEmail()}</h2>
                        <h2 class='${user.isBanStatus() ? "userBanned" : "userNotBanned"}'>
                            <c:if test="${user.isBanStatus()}">
                                <fmt:message key="user_banned"/>
                            </c:if>
                            <c:if test="${!user.isBanStatus()}">
                                <fmt:message key="user_not_banned"/>
                            </c:if>
                        </h2>
                    </div>
                    <form method="post" action="controller" class="userForm">
                        <input type="hidden" name="task" value="edit_user">
                        <input type="hidden" name="userId" value="${user.getId()}">
                        <input type="hidden" name="userRole" value="${user.getRole()}">
                        <c:if test="${user.isBanStatus()}">
                            <input type="hidden" name="userBanState" value="false">
                            <button class="unbanButton" type="submit"><fmt:message key="user_unban"/></button>
                        </c:if>
                        <c:if test="${!user.isBanStatus()}">
                            <input type="hidden" name="userBanState" value="true">
                            <button class = "banButton" type="submit"><fmt:message key="user_ban"/></button>
                    </c:if>
                    </form>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
