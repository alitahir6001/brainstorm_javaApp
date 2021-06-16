<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- c:out ; c:foreach; c:if  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<title>Show One Idea</title>
</head>
<body>

	<h1 class="jumbotron"> Show Page</h1>
	
	<p> ${thisIdea.idea} </p>
	<a href="/dashboard">Home</a>
	


	<p>Creator of Idea: <c:out value="${thisIdea.creator.firstName}"/> </p>
<a href="/ideas/${thisIdea.id}/edit"><button class="btn btn-warning"> Edit</button></a>
	
	
	

</body>
</html>