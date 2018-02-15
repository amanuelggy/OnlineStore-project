<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel = "stylesheet" href = "/css/product_form.css" type = "text/css">
<title>Adding Product page</title>
</head>
<body>
	<div class = "container">
			<div class = "header">
						<table class = "title_table">
						   <tr class = "title_rows">
						   		<td class = "title_rows"><a href = "/">Home</a></td>
						   		<td class = "title_rows"><a class = "current" href = "/api/products/page">Create your product</a></td>
						   		<td class = "title_rows"><a href = "/api/products/myproducts">My products</a></td>
						   		<td class = "title_rows"><a href = "/api/wishlist">WishList</a></td>
						   		<td class = "title_rows"><a href = "/api/cart"><span class="glyphicon glyphicon-shopping-cart"></span><label style = "color:white; margin-left: -10px; position:relative; top: -10px; width: 16px; height: 16px; border-radius: 50%; line-height:18px; text-align: center; background: red; font-size: 12px;"><c:out value = "${ cartsize }"></c:out></label></a></td>				   
						  		<td class = "title_rows"><a href = "/user/setting">Account</a></td>			  
						    		<td class = "title_rows">
						    			<form id="logoutForm" method="POST" action="/logout">
								        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        <input class = "logout_button" type="submit" value="Logout" />
					    	    			</form>
					    	    		</td>
					    	    </tr>
					   </table>
				</div>
			
			<div class = "pro_form">
					<h4>Add your product information:</h4>
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
				</div>
	</div>
</body>
</html>