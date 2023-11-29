<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["error"]}' var="error"/>
<c:set value='${requestScope["message"]}' var="message"/>
<div class="modalEdit">
    <form method="post" action="controller">
        <label>Image name</label>
        <input type="text" name="productImage">
        <label>Name</label>
        <input type="text" name="productName">
        <label>Price</label>
        <input type="text" name="productPrice">
    </form>
</div>
