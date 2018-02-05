<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All User Products</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
<p><a href = "/">Home</a> &nbsp; &nbsp; <a href = "/api/wishlist"> My WishList</a>&nbsp; &nbsp; <a href = "/api/cart">Cart</a></p>
	<c:forEach items = "${ products }" var = "pro">
		<div>
			<div>
				<img alt="product_image" src="<c:url value = "${ pro.img }" />" />
			</div>
			<div>
				<h2><c:out value="${ pro.name }"></c:out></h2>
				price: $<label><c:out value="${ pro.price }"></c:out></label>
				+ Shipping <c:if test="${ pro.shipfee != Null }">
					$<label><c:out value="${ pro.shipfee }"></c:out></label>
				</c:if>
				<c:if test= "${ pro.shipStatus == true }">
					<label>Free</label>
				</c:if>
				<p>About the product: <label><c:out value="${ pro.description }"></c:out></label></p>
				<p>Product detail: <label><c:out value="${ pro.detail }"></c:out></label></p>
			</div>
			
		</div>
	</c:forEach>
	
</body>
</html>