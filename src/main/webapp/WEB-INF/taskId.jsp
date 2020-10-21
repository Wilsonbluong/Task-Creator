<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${task.name}"/></title>
</head>
<body>
	<a href="/tasks">Back</a>
	<h1>Task: <c:out value="${task.name}"/></h1>
	<p>Creator: <c:out value="${task.creator.name}"/></p>
	<p>Assignee: <c:out value="${task.assignee.name}"/></p>
	<p>
		<td>Priority:</td>
		<c:if test="${task.priority==1}">
			<td>Low</td>
		</c:if>
		<c:if test="${task.priority==2}">
			<td>Medium</td>
		</c:if>
		<c:if test="${task.priority==3}">
			<td>High</td>
		</c:if>
	</p>
	
	<form:form action="/tasks/${task.id}/delete" method="POST">
		<input type="submit" value="Delete"/>
	</form:form>
	
	<a href="/tasks/${task_id}/edit">Edit</a>
	
</body>
</html>