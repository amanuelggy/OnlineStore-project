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
<link rel = "stylesheet" type = "text/css" href = "css/login.css">
<title>Login Page</title>
</head>
<body>
		<div class = "container">
					<div class = "mains_top" class = "top">
						<table class = "title_table">
							   <tr class = "title_rows">
							   		<td class ="mains_nav" class = "title_rows"><a href = "/main">Home</a></td>
							   		<td class ="mains_nav" class = "title_rows"><a class = "current" href = "/login">Login / Registration</a></td>
							   		<td class ="mains_nav" class = "title_rows"><a href = "/main/about">About</a></td>
							   </tr>
						</table>
					</div>
			<!-- <p><a href = "/main">Home</a> &nbsp; &nbsp; <a href = "/login">Login</a> &nbsp; &nbsp; <a href = "/registration">Register</a></p> -->
		    <c:if test="${logoutMessage != null}">
		        <c:out value="${logoutMessage}"></c:out>
		    </c:if>
		    
		    <c:if test="${errorMessage != null}">
		        <c:out value="${errorMessage}"></c:out>
		    </c:if>
		    <div class = "log_reg">
		    
			   <div class = "login">
			   		<h1>Login</h1>
				    <form method="POST" action="/login">
				        <p>
				            <label for="username">Email</label>
				            <input type="text" id="username" name="username"/>
				        </p>
				        <p>
				            <label for="password">Password</label>
				            <input type="password" id="password" name="password"/>
				        </p>
				        
				        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        <input type="submit" value="Login!"/>
				    </form>
			    </div>
			    <div>
			    		<label class = "or">(or)</label>
			    </div>
			    <div class = "regi">
			    		<h1>Register!</h1>
			    
			    		<p><form:errors path="user.*"/></p>
			    
				    <form:form method="POST" action="/registration" modelAttribute="user">
				        
				        <p>
				            <form:label path="firstname">First Name:</form:label>
				            <form:input path="firstname"/>
				        </p>
				        <p>
				            <form:label path="lastname">Last Name:</form:label>
				            <form:input path="lastname"/>
				        </p>
				         <p>
				            <form:label path="email">Email:</form:label>
				            <form:input path="email"/>
				        </p>
				  
				        <p>
				            <form:label path="password">Password:</form:label>
				            <form:password path="password"/>
				        </p>
				        <p>
				            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
				            <form:password path="passwordConfirmation"/>
				        </p>
				        <input type="submit" value="Register!"/>
				    </form:form>
			    </div>
		    </div>
    </div>
</body>
</html>