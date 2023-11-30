<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<header>
    <div class="displayContainer">
        <h1 class="shopName">Shop Name</h1>
        <nav>
            <ul class="navItems">
                <li><a href="/controller?task=get_products"><fmt:message key="catalogue_name"/></a></li>
                <c:choose>
                    <c:when test="${user.getRole() == 1}">
                        <c:if test="${user.getRole() == 1}">
                            <li><a href="/controller?task=get_users"><fmt:message key="admin_panel"/></a></li>
                            <li><a href="/controller?task=get_coupons"><fmt:message key="admin_sales"/></a></li>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/controller?task=get_user_cart"><fmt:message key="user_cart"/></a></li>
                    </c:otherwise>
                </c:choose>
                <li><p class="userHeaderName">${user.getName()}</p></li>
                <li>
                    <form class="changeLanguageForm" action="controller" method="post">
                        <input type="hidden" name="task" value="change_language">
                        <select name="language" onchange="this.form.submit()">
                            <option value="en" ${sessionScope.lang == 'en' ? 'selected' : ''}>English</option>
                            <option value="ru" ${sessionScope.lang == 'ru' ? 'selected' : ''}>Русский</option>
                        </select>
                    </form>
                </li>
                <li>
                    <form class="logoutForm" action="controller" method="POST">
                        <input type="hidden" name="task" value="do_logout">
                        <button type="submit">
                            <fmt:message key="log_out"/>
                        </button>
                </form>
                </li>
            </ul>
        </nav>
    </div>
</header>
