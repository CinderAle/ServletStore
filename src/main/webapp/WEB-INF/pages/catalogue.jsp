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
    <title>Shop catalogue</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/catalogueStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/headerStyle.css">
    <style>
        .sale::before {
            content: '<fmt:message key="product_sale"/>';
        }
    </style>
</head>
<body>
<%@ include file="../parts/header.jsp" %>
<main class="shopItemsList">
    <div class="displayContainer">
        <div>${error}</div>
        <c:set value='${requestScope["productsList"]}' var="productsList"/>
        <c:set value='${requestScope["saleMap"]}' var="saleMap"/>
        <c:forEach var="product" items="${saleMap}">
            <div class='shopItem ${product.value != null ? "sale" : ""}'>
                <div class="imageContainer">
                    <img src="${pageContext.servletContext.contextPath}/images/${product.key.getImagePath()}">
                </div>
                <div class="shopItemDescription">
                    <h2 class="productName">${product.key.getName()}</h2>
                    <c:if test="${product.value == null}">
                    <h2 class="productPrice">${product.key.getPrice()}</h2>
                    </c:if>
                    <c:if test="${product.value != null}">
                        <h2 class="productPrice"><span class="oldPrice">${product.key.getPrice()}</span>${String.format("%.2f", (100-product.value.getSale()) / 100 * product.key.getPrice())}</h2>
                    </c:if>
                    <c:if test="${user.getRole() == 1}">
                        <form class="editProductForm" method="post" action="controller" style="display: none">
                            <input type="hidden" name="task" value="edit_product">
                            <input type="hidden" name="productId" value="${product.key.getId()}">
                            <label><fmt:message key="product_name"/></label>
                            <input type="text" name="productName" value="${product.key.getName()}">
                            <label><fmt:message key="product_image_name"/></label>
                            <input type="text" name="productImage" value="${product.key.getImagePath()}">
                            <label><fmt:message key="product_price"/></label>
                            <input type="text" name="productPrice" value="${product.key.getPrice()}">
                            <button type="submit"><fmt:message key="edit_btn"/></button>
                        </form>
                        <button class="editProductButton"><fmt:message key="edit_btn"/></button>
                        <form method="post" action="controller">
                            <input type="hidden" name="task" value="remove_product">
                            <input type="hidden" name="productId" value="${product.key.getId()}">
                            <button type="submit"><fmt:message key="delete_btn"/></button>
                        </form>
                    </c:if>
                    <c:if test="${user.getRole() == 2}">
                        <form method="post" action="controller">
                            <input type="hidden" name="task" value="add_cart_item">
                            <input type="hidden" name="productId" value="${product.key.getId()}">
                            <input type="hidden" name="userId" value="${user.getId()}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit"><fmt:message key="add_to_cart"/></button>
                        </form>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
<footer>
    <div class="displayContainer">
        <div class="supportPart">

        </div>
        <div class="documentsPart">

        </div>
        <div class="socialPart">

        </div>
        <div class="paymentsPart">

        </div>
    </div>
</footer>
<script>
    const editProductButtons = document.getElementsByClassName('editProductButton');
    if(editProductButtons != null) {
        Array.from(editProductButtons).forEach(button => {
            button.addEventListener('click', () => {
                let closestForm = button.parentElement.getElementsByClassName('editProductForm').item(0);
                if (closestForm.style.display === 'none') {
                    button.innerHTML = '<fmt:message key="hide_btn"/>';
                    closestForm.style.display = 'flex';
                    button.parentElement.getElementsByClassName('productPrice').item(0).style.display = 'none';
                    button.parentElement.getElementsByClassName('productName').item(0).style.display = 'none';
                    button.parentElement.getElementsByClassName('imageContainer').item(0).style.display = 'none';
                } else {
                    button.innerHTML = '<fmt:message key="edit_btn"/>';
                    closestForm.style.display = 'none';
                    button.parentElement.getElementsByClassName('productPrice').item(0).style.display = 'block';
                    button.parentElement.getElementsByClassName('productName').item(0).style.display = 'block';
                    button.parentElement.getElementsByClassName('imageContainer').item(0).style.display = 'block';
                }
            })
        });
    }
</script>
</body>
</html>
