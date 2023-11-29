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
                <li><a href="/controller?task=get_products">Catalogue</a></li>
                <c:choose>
                    <c:when test="${user.getRole() == 1}">
                        <c:if test="${user.getRole() == 1}">
                            <li><a href="/controller?task=get_users">Panel</a></li>
                            <li><a href="#">Sales</a></li>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/controller?task=get_user_cart">Cart</a></li>
                    </c:otherwise>
                </c:choose>
                <li><p class="userHeaderName">${user.getName()}</p></li>
                <li>
                    <form class="logoutForm" action="controller" method="POST">
                        <input type="hidden" name="task" value="do_logout">
                        <button type="submit">
                            Log out
                        </button>
                </form>
                </li>
            </ul>
        </nav>
    </div>
</header>
