<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adding Product page</title>
</head>
<body>
	<p><a href = "/">Home</a> &nbsp; &nbsp; <a href = "/api/products/myproducts">All your products</a> &nbsp; &nbsp;
	   <a href = "/api/wishlist"> My WishList</a>&nbsp; &nbsp; <a href = "/api/cart">Cart</a></p>
	   
	<h3>Add your product</h3>
	<p><form:errors path="product.*"/></p>
		<form:form method = "POST" action = "/api/products/save" modelAttribute="product">
			<p>
				<form:label path="name">Product name: </form:label>
				<form:input path="name"/>
			</p>
			<p>
				<form:label path="img">Image URL: </form:label>
				<form:input path="img"/>
			</p>
			<p>
				<form:label path="detail">Detail: </form:label>
				<form:input path="detail"/>
			</p>
			<p>
				<form:label path="description">Description (specification): </form:label>
				<form:input path="description"/>
			</p>
			<p>
				<form:label path="price">Price: </form:label>
				<form:input path="price"/>
			</p>
			<p>
				<form:label path="shipfee">Shipping fee: </form:label>
				<form:input type = "number" path="shipfee" placeholder = "optional"/> or
				
				<label name="shipStatus">Free shipping: </label>
				<input type="checkbox" name="shipStatus" value = "true"/> Yes
				
			</p>
		<%-- 	<p>
				<form:label path="numOfProd">Quantity: </form:label>
				<form:input path = "numOfProd" type="number"/>
			</p> --%>
			<input type = "submit" value = "Submit">
		</form:form>
	
</body>
</html>