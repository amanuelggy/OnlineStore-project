<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
</head>
<body>
	
	<p><a href = "/login">login</a> &nbsp; &nbsp; <a href = "/registration">Register</a></p>
	<form method = "POST" action = "/main">
				<label for="product"></label>
		            <input type="text" id="product" name="product" placeholder= "search" /> &nbsp;
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        <input type="submit" value="Search"/>
			   		<button name = "allproduct" value = "allproduct">All products</button><br><br>
	
		<c:forEach items = "${ products }" var = "pro">
			<c:if test="${ pro.name.toLowerCase().indexOf(product.toLowerCase()) > -1 || pro.name.toLowerCase().indexOf(product.toLowerCase()) == null}">
			<div>
				
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
					
					</div>
				
				
					<form:form method = "POST" action="/api/wishlist/${ pro.id }" modelAttribute = "wishlist">
						<input type = "submit" value = "Add to WishList"/>
					</form:form><br><br>
					<form:form method = "POST" action="/api/cart/${ pro.id }" modelAttribute = "cart">
						<input type = "submit" value = "Add to Cart"/>
					</form:form>
					
					
			</div>
			</c:if>
		</c:forEach>
	</form>
</body>
</html>