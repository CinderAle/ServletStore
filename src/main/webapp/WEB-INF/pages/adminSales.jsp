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
    <title>Admin Sales</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/headerStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/salesStyle.css">
</head>
<body>
<%@ include file="../parts/header.jsp" %>
<c:set value='${requestScope["coupons"]}' var="coupons"/>
<c:set value='${requestScope["sales"]}' var="sales"/>
<c:set value='${requestScope["notSaleProducts"]}' var="notSaleProducts"/>
    <div class="displayContainer">
        <div class="errorsContainer">${error}</div>
        <div class="addBonusesContainer">
            <form class="addCouponForm" method="post" action="controller">
                <input type="hidden" name="task" value="add_coupon">
                <label><fmt:message key="coupon_name"/></label>
                <input type="text" name="newCouponName" value="">
                <label><fmt:message key="coupon_discount"/></label>
                <input type="text" name="newCouponSale" value="">
                <button type="submit"><fmt:message key="add_btn"/></button>
            </form>
            <c:if test="${notSaleProducts.size() > 0}">
            <form class="addSaleForm" method="post" action="controller">
                <input type="hidden" name="task" value="add_sale">
                <label><fmt:message key="not_sale_products"/></label>
                <select name="newProductSale">
                    <c:forEach items="${notSaleProducts}" var="notSaleProduct">
                        <option value="${notSaleProduct.getId()}">${notSaleProduct.getName()}</option>
                    </c:forEach>
                </select>
                <label><fmt:message key="sale_discount"/> %</label>
                <input type="text" name="newSaleSize" value="">
                <button type="submit"><fmt:message key="add_btn"/></button>
            </form>
            </c:if>
            <c:if test="${notSaleProducts.size() == 0}">
                <h1 class="noItemsText"><fmt:message key="all_products_sale"/></h1>
            </c:if>
        </div>
        <div class="bonusesContainer">
            <div class="couponsContainer">
                <c:forEach var="coupon" items="${coupons}">
                    <div class="couponItem">
                        <h2 class="couponName">${coupon.getName()}</h2>
                        <h2 class="couponSale">${coupon.getSale()}</h2>
                        <form class="editCouponForm" method="post" action="controller" style="display: none">
                            <input type="hidden" name="couponId" value="${coupon.getId()}">
                            <input type="hidden" name="task" value="edit_coupon">
                            <label><fmt:message key="coupon_name"/></label>
                            <input type="text" name="newCouponName" required value="${coupon.getName()}">
                            <label><fmt:message key="coupon_discount"/></label>
                            <input type="text" name="newCouponSale" required value="${coupon.getSale()}">
                            <button type="submit"><fmt:message key="edit_btn"/></button>
                        </form>
                        <button class="couponFieldsButton"><fmt:message key="edit_btn"/></button>
                        <form class = "deleteCouponForm" method="post" action="controller">
                            <input type="hidden" name="couponId" value="${coupon.getId()}">
                            <input type="hidden" name="task" value="remove_coupon">
                            <button type="submit"><fmt:message key="delete_btn"/></button>
                        </form>
                    </div>
                </c:forEach>
                <c:if test="${coupons.size() == 0}">
                    <h1 class="noItemsText"><fmt:message key="no_coupons"/></h1>
                </c:if>
            </div>
            <div class="salesContainer">
                <c:forEach var="sale" items="${sales}">
                    <div class="saleItem">
                        <div class="imageContainer">
                            <img src="${pageContext.servletContext.contextPath}/images/${sale.key.getImagePath()}" alt="Product Image">
                        </div>
                        <h2 class="saleProductName">${sale.key.getName()}</h2>
                        <h2 class="saleValue">${sale.value.getSale()}%</h2>
                        <form class="editSaleForm" method="post" action="controller" style="display: none">
                            <input type="hidden" name="task" value="edit_sale">
                            <input type="hidden" name="saleProductId" value="${sale.key.getId()}">
                            <label><fmt:message key="sale_discount"/> %</label>
                            <input type="text" name="newSaleValue" value="${sale.value.getSale()}" required>
                        </form>
                        <button class="saleFieldsButton"><fmt:message key="edit_btn"/></button>
                        <form class="deleteSaleForm" method="post" action="controller">
                            <input type="hidden" name="removeSaleProduct" value="${sale.key.getId()}">
                            <input type="hidden" name="task" value="remove_sale">
                            <button><fmt:message key="delete_btn"/></button>
                        </form>
                    </div>
                </c:forEach>
                <c:if test="${coupons.size() == 0 && sales.size() == 0}">
                    <h1 class="noItemsText"><fmt:message key="no_sales"/></h1>
                </c:if>
            </div>
        </div>
    </div>
<script>
    const editCouponButtons = document.getElementsByClassName('couponFieldsButton');
    const editSaleButtons = document.getElementsByClassName('saleFieldsButton');
    if(editCouponButtons != null) {
        Array.from(editCouponButtons).forEach(button => {
            button.addEventListener('click', () => {
                let closestForm = button.parentElement.getElementsByClassName('editCouponForm').item(0);
                if (closestForm.style.display === 'none') {
                    button.innerHTML = '<fmt:message key="hide_btn"/>';
                    closestForm.style.display = 'block';
                    button.parentElement.getElementsByClassName('couponName').item(0).style.display = 'none';
                    button.parentElement.getElementsByClassName('couponSale').item(0).style.display = 'none';
                } else {
                    button.innerHTML = '<fmt:message key="edit_btn"/>';
                    closestForm.style.display = 'none';
                    button.parentElement.getElementsByClassName('couponName').item(0).style.display = 'block';
                    button.parentElement.getElementsByClassName('couponSale').item(0).style.display = 'block';
                }
            })
        });
    }
    if(editSaleButtons != null) {
        Array.from(editSaleButtons).forEach(button => {
            button.addEventListener('click', () => {
                let closestForm = button.parentElement.getElementsByClassName('editSaleForm').item(0);
                if (closestForm.style.display === 'none') {
                    button.innerHTML = '<fmt:message key="hide_btn"/>';
                    closestForm.style.display = 'block';
                    button.parentElement.getElementsByClassName('saleValue').item(0).style.display = 'none';
                } else {
                    button.innerHTML = '<fmt:message key="edit_btn"/>';
                    closestForm.style.display = 'none';
                    button.parentElement.getElementsByClassName('saleValue').item(0).style.display = 'block';
                }
            })
        });
    }
</script>
</body>
</html>
