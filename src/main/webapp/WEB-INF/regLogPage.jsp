<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h1>Welcome</h1>
    <p><form:errors path="user.*"/></p>
    <form:form method="POST" action="/register" modelAttribute="user">
    	<fieldset>
	    	<legend>Register</legend>
	    	<p>
	            <form:label path="name">Name:</form:label>
	            <form:input type="text" path="name"/>
	        </p>
	        <p>
	            <form:label path="email">Email:</form:label>
	            <form:input type="email" path="email"/>
	        </p>
	        <p>
	            <form:label path="password">Password:</form:label>
	            <form:password path="password"/>
	        </p>
	        <p>
	            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password path="passwordConfirmation"/>
	        </p>
	        <input type="submit" value="Register"/>
	   </fieldset>
    </form:form>
    
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
    	<fieldset>
		    <legend>Login</legend>
	        <p>
	            <label for="email">Email</label>
	            <input type="email" id="email" name="email"/>
	        </p>
	        <p>
	            <label for="password">Password</label>
	            <input type="password" id="password" name="password"/>
	        </p>
	        <input type="submit" value="Login"/>
	   </fieldset>
    </form> 
</body>
</html>