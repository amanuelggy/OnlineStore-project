<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel = "stylesheet" type = "text/css" href = "/css/style.css">
<title>Setting Info (User Info)</title>
</head>
<body>
	<div class = "container">
				<div class = "header">
						<table class = "title_table">
						   <tr class = "title_rows">
						   		<td class = "title_rows"><a href = "/">Home</a></td>
						   		<td class = "title_rows"><a href = "/api/products/page">Create your product</a></td>
						   		<td class = "title_rows"><a href = "/api/products/myproducts">My products</a></td>
						   		<td class = "title_rows"><a href = "/api/wishlist">WishList</a></td>
						   		<td class = "title_rows"><a href = "/api/cart"><span class="glyphicon glyphicon-shopping-cart"></span><label style = "color:white; margin-left: -10px; position:relative; top: -10px; width: 16px; height: 16px; border-radius: 50%; line-height:18px; text-align: center; background: red; font-size: 12px;"><c:out value = "${ cartsize }"></c:out></label></a></td>				   
						  		<td class = "title_rows"><a class = "current" href = "/user/setting">Account</a></td>			  
						    		<td class = "title_rows">
						    			<form id="logoutForm" method="POST" action="/logout">
								        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        <input class = "logout_button" type="submit" value="Logout!" />
					    	    			</form>
					    	    		</td>
					    	    </tr>
					   </table>
				</div>
		    <div class = "setting">
			    	<p>First Name: <c:out value="${ currentUser.firstname }"></c:out></p>
			    	<p>Last Name: <c:out value="${ currentUser.lastname }"></c:out></p>
			    	<p>Email: <c:out value="${ currentUser.email }"></c:out></p>
			    	<p>Sign up date: <fmt:formatDate type = "date" value="${ currentUser.createdAt }" pattern = "MM/dd/yyyy" /></p>
			    	<p>Last Sign in: <fmt:formatDate type = "date" value="${ currentUser.createdAt }" pattern = "MM/dd/yyyy" /></p>
		    </div><br>
    </div>
</body>
</html>