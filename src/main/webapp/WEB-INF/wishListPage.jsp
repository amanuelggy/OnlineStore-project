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
<link rel = "stylesheet" type = "text/css" href = "/css/cart.css">
<title>WishList</title>
</head>
<body>
<div class = "container">
	<div class = "header">
				<table class = "title_table">
				   <tr class = "title_rows">
				   		<td class = "title_rows"><a href = "/">Home</a></td>
				   		<td class = "title_rows"><a href = "/api/products/page">Create your product</a></td>
				   		<td class = "title_rows"><a href = "/api/products/myproducts">My products</a></td>
				   		<td class = "title_rows"><a class = "current" href = "/api/wishlist">WishList</a></td>
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
		
	
	
		<c:forEach items = "${ products }" var = "pro">
			
			<c:if test="${ pro.getUser().getId() == pro.getWishlist().getUser().getId() }">
				<div class = "pro_div" class = "product_div">
					<div class = "img_div">
						<div class = "img">
							<a href = "/api/products/${ pro.id }"><img class = "img" alt="product_image" src="<c:url value = "${ pro.img }" />" /></a>
						</div>
						<div class = "desc_div">
							<div class = "title_div">
								<div class = "title">
									<a href = "/api/products/${ pro.id }"><c:out value="${ pro.name }"></c:out></a>
								</div>
								by <span><c:out value="${ pro.getUser().getFirstname() }"></c:out></span>
							</div>
							<div class = "desc_info">
								$<span><c:out value= "${ pro.price }"></c:out></span>
								<c:if test="${ pro.shipfee != Null }">
									$<span> + Shipping<c:out value="${ pro.shipfee }"></c:out></span>
								</c:if>
								<c:if test= "${ pro.shipStatus == true }">
									<span> + FREE Shipping</span>
								</c:if>
							</div>
							<div class = "desc">
								<label>Product Description:</label> <br><span class = "desc_text"><c:out value="${ pro.description }"></c:out></span>
							</div>
						
						</div>
					</div>
					<div class = "cart_wish">
						<div class = "wish_button">
							<form:form method = "POST" action = "/api/wishlist/delete/${pro.id}">
								<input class = "rm_wish" type = "submit" value = "Remove from Wishlist">
							</form:form>
						</div>
						<div class = "cart_form">
							<form:form method = "POST" action="/api/cart/${ pro.id }" modelAttribute = "cart">
								<input class = "cart_button" type = "submit" value = "Add to Cart"/>
							</form:form>
						</div>
					</div>
				</div>
			</c:if><br>
		</c:forEach>
	</div>
</body>
</html>