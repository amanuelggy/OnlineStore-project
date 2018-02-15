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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel = "stylesheet" href = "/css/singlePro.css">
<title>Single Product Detail</title>
</head>
<body>
	<c:set var = "now" value = "<%= new java.util.Date()%>" />
	<div class = "container">
			<div class = "mains_top" class = "top">
				<table class = "title_table">
					   <tr class = "title_rows">
					   		<td class ="mains_nav" class = "title_rows"><a href = "/main">Home</a></td>
					   		<td class ="mains_nav" class = "title_rows"><a href = "/login">Login / Registration</a></td>
					   		<td class ="mains_nav" class = "title_rows"><a href = "/about">About</a></td>
					   </tr>
				</table>
			</div>
				<div class = "content">
					<div class = "product_main">
							<div class = "img_div">
								<img class = "img" alt="product_image" src="<c:url value = "${ product.img }" />" />
							</div>
							<div class = "desc_div">
									<div class = "title_div">
										<div class = "title">
											<span><c:out value="${ product.name }"></c:out></span>
										</div>
										 <span class = "name">by <c:out value="${ product.getUser().getFirstname() }"></c:out></span>
									</div>
									<div class = "desc_info">
										<span class = "price">$ <c:out value= "${ product.price }"></c:out></span>
										<c:if test="${ product.shipfee != Null }">
											$<span> + Shipping<c:out value="${ product.shipfee }"></c:out></span>
										</c:if>
										<c:if test= "${ product.shipStatus == true }">
											<span> + FREE Shipping</span>
										</c:if>
									</div>
									<div class = "desc">
										<label>Product Description:</label> <br><span class = "desc_text"><c:out value="${ product.description }"></c:out></span><br>
										
									</div>
									
							</div>
							<div class = "cart_wish">
								<div class = "cart_form">
									<form:form method = "POST" action="/api/cart/${ product.id }" modelAttribute = "cart">
										<input class = "cart_button" type = "submit" value = "Add to Cart"/>
									</form:form>
								 </div>
							 	<%-- <div class = "wish">
									<form:form method = "POST" action="/api/wishlist/${ product.id }" modelAttribute = "wishlist">
										<input class = "wish_botton" type = "submit" value = "Add to WishList"/>
									</form:form>
								</div> --%>
								
							</div>	
					</div>
					
					<div class = "mains_detail_div">
						<label>Product information: </label><br>
						<span class = "detail"><c:out value="${ product.detail }"></c:out></span>
					</div>
					
					<div class = "mains_comments">
						<label class = "fedbacks">Customer reviews:</label>
						<c:forEach items = "${ product.getComments() }" var = "comment">
							
							<div class = "comment_list">
								<div class = "comment_div">
									<i class="fa fa-user-circle-o" style="font-size:36px"></i>
									<label class = "user_name"><c:out value = "${ comment.getUser().getFirstname() }"></c:out>:</label><br>
									<span class = "comm_date"><fmt:formatDate pattern = "dd/MM/yyyy" value="${ comment.createdAt }" /></span>
								</div>
								<div class = "comment">
									<span><c:out value="${comment.comment }"></c:out></span>
								</div>
							</div>
							
						</c:forEach>
						<%-- <form:form action="/api/comments/save/${ product.id }" method = "POST" modelAttribute = "com">
							<form:label path = "comment">Comment:</form:label><br>
							<form:textarea path = "comment" rows = "4" cols = "50"></form:textarea><br>
							<input class = "comment_botton" type = "submit" value = "submit">
						</form:form> --%>
					</div>
			 </div>
					
			
		
	</div>
</body>
</html>