<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Single Product Detail</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
		<p><a href = "/">Home</a> &nbsp; &nbsp; <a href = "/api/products/myproducts">My products</a>
		   &nbsp; &nbsp; <a href = "/api/cart">Cart</a></p>
		<div>
			<div>
				<img alt="product_image" src="<c:url value = "${ product.img }" />" />
			</div>
			<div>
				<h2><c:out value="${ product.name }"></c:out></h2>
				price: $<label><c:out value= "${ product.price }"></c:out></label>
				+ Shipping <c:if test="${ product.shipfee != Null }">
					$<label><c:out value="${ product.shipfee }"></c:out></label>
				</c:if>
				<c:if test= "${ product.shipStatus == true }">
					<label>Free</label>
				</c:if>
				<p>About the product: <label><c:out value="${ product.description }"></c:out></label></p>
				<p>Product detail: <label><c:out value="${ product.detail }"></c:out></label></p>
			</div>
		</div>
		
			<div>
				<h3>Rate and give feedback on the product</h3>
				<div>
					<c:forEach items = "${ product.getComments() }" var = "comment">
						<p><c:out value="${comment.comment }"></c:out></p>
						
					</c:forEach>
					<form:form action="/api/comments/save/${ product.id }" method = "POST" modelAttribute = "com">
						<form:label path = "comment">Comment:</form:label><br>
						<form:textarea path = "comment" rows = "4" cols = "50"></form:textarea><br>
						<input type = "submit" value = "submit">
						
					</form:form>
				</div>
			</div>
			<div>
			
			</div>
		

</body>
</html>