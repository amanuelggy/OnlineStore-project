<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
	<p><a href = "/api/products/page">Sell your product</a> &nbsp; &nbsp; 
	   <a href = "/api/products/myproducts">All your products</a> &nbsp; &nbsp; 
	   <a href = "/api/wishlist"> My WishList</a>&nbsp; &nbsp; <a href = "/api/cart">Cart</a> &nbsp; &nbsp;
	   <a href = "/user/setting">Account</a></p>
	
    <h1>Welcome, <c:out value="${currentUser.firstname}"></c:out></h1>

	<c:forEach items = "${ products }" var = "pro">
		<div>
			<div>
				<a href = "/api/products/${ pro.id }"><img alt="product_image" src="<c:url value = "${ pro.img }" />" /></a>
			</div>
			<div>
				<h2><a href = "/api/products/${ pro.id }"><c:out value="${ pro.name }"></c:out></a></h2>
				price: $<label><c:out value= "${ pro.price }"></c:out></label>
				<c:if test="${ pro.shipfee != Null }">
					$<label> + Shipping<c:out value="${ pro.shipfee }"></c:out></label>
				</c:if>
				<c:if test= "${ pro.shipStatus == true }">
					<label> + Free Shipping</label>
				</c:if>
				<p>About the product: <label><c:out value="${ pro.description }"></c:out></label></p>
				<p>Product detail: <label><c:out value="${ pro.detail }"></c:out></label></p>
				
			
			</div>
				<form:form method = "POST" action="/api/wishlist/${ pro.id }" modelAttribute = "wishlist">
					<input type = "submit" value = "Add to WishList"/>
				</form:form><br><br>
				<form:form method = "POST" action="/api/cart/${ pro.id }" modelAttribute = "cart">
					<input type = "submit" value = "Add to Cart"/>
				</form:form>
				
				
		</div>
	</c:forEach>
</body>
</html>