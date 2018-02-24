<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel = "stylesheet" type = "text/css" href = "css/style.css">
<title>Main page</title>
</head>
<body>
	<div class = "container">
		
		<div class = "mains_top" class = "top">
			<table class = "title_table">
				   <tr class = "title_rows">
				   		<td class ="mains_nav" ><a class = "current" href = "/main">Home</a></td>
				   		<td class ="mains_nav" ><a href = "/login">Login / Registration</a></td>
				   		<td class ="mains_nav"  ><a href = "/main/about">About</a></td>
				   </tr>
			</table>
		</div>
		<label class = "mains_welcome">Welcome to worldstore.com online store, where you can buy products from anywhere from the world and sell your product to the world.</label>
		<p class = "mains_welcome">Please <a href = "/login">login/register to buy or sell your products.</a></p>
		<form action = "/main" method = "POST">
			<label for="product"></label>
				            <input class = "search_input" type="text" id="product" name="product" placeholder= " search" /> &nbsp;
					        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					        <input class = "search_button" type="submit" value="Search"/>
					   		<button class = "search_button" name = "allproduct" value = "allproduct">All products</button>
		</form>
		<div class = "mains_main">
			<c:forEach items = "${ products }" var = "pro">
				<c:if test="${ pro.name.toLowerCase().indexOf(product.toLowerCase()) > -1 || pro.name.toLowerCase().indexOf(product.toLowerCase()) == null}">
					<%-- <form method = "POST" action = "/main"> --%>
									<%-- <label for="product"></label>
							            <input class = "search_input" type="text" id="product" name="product" placeholder= " search" /> &nbsp;
								        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        <input class = "search_button" type="submit" value="Search"/>
								   		<button class = "search_button" name = "allproduct" value = "allproduct">All products</button> --%>
								   		<br><br>
								<%-- <c:forEach items = "${ products }" var = "pro">
									<c:if test="${ pro.name.toLowerCase().indexOf(product.toLowerCase()) > -1 || pro.name.toLowerCase().indexOf(product.toLowerCase()) == null}"> --%>
										<div class = "product_div">
											<div class = "img_div">
													<div class = "img">
														<a href = "/main/${ pro.id }"><img class = "img" alt="product_image" src="<c:url value = "${ pro.img }" />" /></a>
													</div>
													<div class = "desc_div">
														<div class = "title_div">
															<div class = "title">
																<a href = "/main/${ pro.id }"><c:out value="${ pro.name }"></c:out></a>
															</div>
															<div class = "comm_div">
																<a href = "/main/${ pro.id }">
																	<c:if test="${ fn:length(pro.getComments()) > 1 }">
																		<span>(<c:out value="${ fn:length(pro.getComments()) }"></c:out> customer reviews)</span>
																	</c:if>
																	<c:if test="${ fn:length(pro.getComments()) <= 1 }">
																		<span>(<c:out value="${ fn:length(pro.getComments()) }"></c:out> customer review)</span>
																	</c:if>
																</a>
															</div>
															by <span><c:out value="${ pro.getUser().getFirstname() }"></c:out></span>
														</div>
														<div class = "desc_info">
															$<span><c:out value= "${ pro.price }"></c:out></span>
															<c:if test="${ pro.shipfee != Null }">
																<span> + Shipping $<c:out value="${ pro.shipfee }"></c:out></span>
															</c:if>
															<c:if test= "${ pro.shipStatus == true }">
																<span> + FREE Shipping</span>
															</c:if>
														</div>
														<div class = "desc">
															<label>Product Description:</label> <br><span class = "desc_text"><c:out value="${ pro.description }"></c:out></span>
														</div>
														<%-- <p>Product detail: <span><c:out value="${ pro.detail }"></c:out></span></p> --%>
													 </div>
											 </div>
											 
												
										</div><br>
									<%-- </form> --%>
										<div class = "mains_cart_wish" class = "cart_wish">
											 	<%-- <div class = "wish_button">
													<form:form method = "POST" action="/api/wishlist/${ pro.id }" modelAttribute = "wishlist">
														<input type = "submit" value = "Add to WishList"/>
													</form:form>
												</div> --%>
												<div class = "cart_form">
													<form:form method = "POST" action="/api/cart/${ pro.id }" modelAttribute = "cart">
														<input class = "mains_cart_button" class = "cart_button" type = "submit" value = "Add to Cart"/>
													</form:form>
												 </div>
											</div><br><br>
									
						</c:if>
					</c:forEach>
				</div>
			<%-- </form> --%>
	</div>
</body>
</html>