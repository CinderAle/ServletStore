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
    <title>Store cart</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/headerStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/cartStyle.css">
</head>
<body>
<%@ include file="../parts/header.jsp" %>
    <div class="displayContainer">
        <div class="cartContainer">
        <div>${error}</div>
        <c:set value='${requestScope["totalSum"]}' var="totalSum"/>
        <c:set value='${requestScope["cartProducts"]}' var="cartProducts"/>
        <c:set value='${requestScope["cartQuantities"]}' var="cartQuantities"/>
        <c:if test="${cartProducts.size() > 0}">
            <div class="itemContainer">
            <c:forEach var="product" items="${cartProducts}">
                <div class="cartItem">
                    <div class="itemImageContainer">
                        <img src="${pageContext.servletContext.contextPath}/images/${cartProducts.get(product.key).getImagePath()}">
                    </div>
                    <h2>${cartProducts.get(product.key).getName()}</h2>
                    <h2>${String.format("%.2f",cartProducts.get(product.key).getPrice())}</h2>
                    <div class="itemQuantityBlock">
                        <form method="post" action="controller">
                            <input type="hidden" name="task" value="edit_cart_item">
                            <input type="hidden" name="userId" value="${user.getId()}">
                            <input type="hidden" name="productId" value="${cartProducts.get(product.key).getId()}">
                            <input type="hidden" name="quantity" value="${cartQuantities.get(product.key) - 1}">
                            <input class = "minusQuantity" type="submit" value="-">
                        </form>
                        <p>${cartQuantities.get(product.key)}</p>
                        <form method="post" action="controller">
                            <input type="hidden" name="task" value="edit_cart_item">
                            <input type="hidden" name="userId" value="${user.getId()}">
                            <input type="hidden" name="productId" value="${cartProducts.get(product.key).getId()}">
                            <input type="hidden" name="quantity" value="${cartQuantities.get(product.key) + 1}">
                            <input class="plusQuantity" type="submit" value="+">
                        </form>
                    </div>
                </div>
            </c:forEach>
            </div>
            <c:set var="coupon" value='${requestScope["coupon"]}'/>
            <c:if test="${coupon == null}">
                <form class="addCouponForm" method="post" action="controller">
                    <input type="hidden" name="task" value="add_user_coupon">
                    <label><fmt:message key="enter_coupon"/></label>
                    <input type="text" name="couponName">
                    <button type="submit"><fmt:message key="enter_btn"/></button>
                </form>
                <h1 class="cartTotalSum"><fmt:message key="total_sum"/>: ${String.format("%.2f",totalSum)}</h1>
            </c:if>
            <c:if test="${coupon != null}">
                <form class="removeCouponForm" method="post" action="controller">
                    <h2>${coupon.getName()}</h2>
                    <input type="hidden" name="task" value="remove_user_coupon">
                    <button type="submit"><fmt:message key="delete_btn"/></button>
                </form>
                <h1 class="cartTotalSum"><fmt:message key="total_sum"/>: ${requestScope["couponSum"]}<span>-${coupon.getSale()}%</span></h1>
            </c:if>
            <form class="buyCartForm" method="post" action="controller">
                <input type="hidden" name="task" value="remove_cart">
                <button type="submit"><fmt:message key="buy_btn"/></button>
            </form>
        </c:if>
        <c:if test="${cartProducts.size() == 0}">
            <h1><fmt:message key="no_products"/></h1>
        </c:if>
        </div>
    </div>
</body>
</html>
