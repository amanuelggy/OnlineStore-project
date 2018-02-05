<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Setting Info (User Info)</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
	<p><a href = "/api/products/page">Sell your product</a> &nbsp; &nbsp; 
	   <a href = "/api/products/myproducts">All your products</a> &nbsp; &nbsp; 
	   <a href = "/api/wishlist"> My WishList</a>&nbsp; &nbsp; <a href = "/api/cart">Cart</a></p>
    <fieldset>
    	<p>First Name: <c:out value="${ currentUser.firstname }"></c:out></p>
    	<p>Last Name: <c:out value="${ currentUser.lastname }"></c:out></p>
    	<p>Email: <c:out value="${ currentUser.email }"></c:out></p>
    	<p>Sign up date: <fmt:formatDate type = "date" value="${ currentUser.createdAt }" pattern = "MM/dd/yyyy" /></p>
    	<p>Last Sign in: <fmt:formatDate type = "date" value="${ currentUser.createdAt }" pattern = "MM/dd/yyyy" /></p>
    </fieldset><br>
</body>
</html>