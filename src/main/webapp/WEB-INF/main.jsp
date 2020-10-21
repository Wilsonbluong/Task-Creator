<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
</head>
<body>
	<div class= "container">

		<div class="header">
			<h1>Welcome, <c:out value="${user.name}"/></h1>
			<a href="/logout">Logout</a>
		</div>
		

		<div class="main">
			<table>
				<thead>
					<tr>
				    	<th>Task</th>
				      	<th>Creator</th>
				      	<th>Assignee</th>
				      	<th>Priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
						    <td><a href="/tasks/${task.id}"><c:out value="${task.name}"/></a></td>
						    <td><c:out value="${task.creator.getName()}"/></td>
						    <td>${task.assignee.getName()}</td>
						    <c:if test="${task.priority==1}">
								<td>Low</td>
							</c:if>
							<c:if test="${task.priority==2}">
								<td>Medium</td>
							</c:if>
							<c:if test="${task.priority==3}">
								<td>High</td>
							</c:if>
					 	</tr>
					 </c:forEach>
				 </tbody>
			</table>
		</div>
		
		<div class="button">
			<a href="/tasks/new">Create</a>
		</div>
	</div>
</body>
</html>