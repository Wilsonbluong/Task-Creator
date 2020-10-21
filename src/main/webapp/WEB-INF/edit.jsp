<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<p>Edit a task</p>
		</div>
		<section>
			<div>
				<div>

					<form:form method="post" action="/tasks/${task.id}/edit" modelAttribute="task">
						<form:input type="hidden" path="id"></form:input> 
						<table>
							<tr>
								<td><form:label path="name">Task:</form:label></td>
								<td><form:input path="name"/></td>
								<td><form:errors path="name"/></td>
							</tr>
							<tr>
							<!-- preload assignee in drop-down menu -->
								<td><form:label path="assignee">Assignee:</form:label></td>
								<td><form:select path="assignee">
										<form:option value="${task.assignee}">${task.assignee.getName()}</form:option>
										
										<!-- List all users from user table -->
										<c:forEach items="${users}" var="user"> 
										
										<!-- List all users except a current selected assignee and a task creator-->
										<!-- check if user name isn't a current selected assignee  -->
											<c:if test="${user.name != task.assignee.getName()}"> 
												
												<!-- check if user name isn't a task creator name -->
												<c:if test="${user.name!=creator.name}">
													
													<form:option value="${user}">
														<c:out value="${user.name}" />
													</form:option>
												</c:if>
											</c:if> 
										</c:forEach> 
									</form:select></td>
								<td><form:errors path="assignee" /></td>
							</tr>
							<tr>
								<td><form:label path="priority">Priority:</form:label></td>
								<td><form:select path="priority">
										<form:option value="1">Low</form:option>
										<form:option value="2">Medium</form:option>
										<form:option value="3">High</form:option>
									</form:select></td>
								<td><form:errors path="priority" /></td>
						</table>
						<!-- creator need to be specify when update a task -->
						<!--  Instead of passing user_id through model you can use a hidden input in form -->
						<!-- <form:input type="hidden" path="creator" value="${creator.id}"/> -->
						<div>
							<input type="submit" value="Edit"/>
						</div>
					</form:form>
				</div>
				<div></div>
			</div>
			

		</section>
	</div>
</body>
</html>