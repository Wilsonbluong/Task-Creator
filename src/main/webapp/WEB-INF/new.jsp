<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create task</title>
</head>
<body>
	<a href="/tasks">Back</a>
	<h1>Create a new task</h1>
	<form:form action="/tasks/new" method="post" modelAttribute="task">
		<p><form:errors path="name"/></p>
		<p>
	        <form:label path="name">Task:</form:label>
	        <form:input path="name"/>
	    </p>
		<p>
			<form:label path="assignee">Assignee:</form:label>
			<form:select path="assignee">
				<form:option value=""></form:option>
	   			<c:forEach items="${users}" var="user">
					<c:if test="${user.name != currentUser.name}">
						<form:option value="${user}">
							<c:out value="${user.name}" />
						</form:option>
					</c:if>
				</c:forEach>
        	</form:select>
   		</p>
   		<p>
        	<form:label path="priority">Priority:</form:label>
	    	<form:select path="priority">
				<form:option value=""></form:option>
				<form:option value="1">Low</form:option>
				<form:option value="2">Medium</form:option>
				<form:option value="3">High</form:option>
   			</form:select>
   		</p>  
	    <input type="submit" value="Create"/>
	</form:form>
	
</body>
</html>