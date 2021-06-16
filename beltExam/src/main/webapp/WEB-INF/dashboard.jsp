<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:foreach; c:if  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Welcome to the Dash</title>
</head>
<body>

	<h1>Welcome ${loggedinuser.firstName}!</h1>
	<br>
	<br>

	

	<table class="table table-dark">
		<thead>
			<tr>

				<th scope="col">Idea</th>
				<th scope="col">Created by</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items='${banana}' var='ideas'>
				<tr>

					<td><a href="/show/${ideas.id}">${ideas.idea}</a></td>
					<td><a href="/show/${ideas.id}">${ideas.creator.firstName}</a></td>
					


				</tr>
			</c:forEach>
		</tbody>
	</table>


	<a href="/createnew"><button class="btn btn-primary">Create
			a new idea!</button></a>
	<a href="/logout"><button class="btn btn-warning">Logout</button></a>
</body>
</html>