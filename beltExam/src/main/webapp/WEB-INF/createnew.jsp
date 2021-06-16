<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<title>Create</title>
</head>
<body>

<h1 class="jumbotron">Create a new Idea!</h1>
<a href="/dashboard">Home</a>
		<br><br>

        <form:form action="/makeidea" method="post" modelAttribute="ideaz">
            <p>
                <form:label path="idea">Idea</form:label>
                <form:errors path="idea"/>
                <form:input path="idea"/>
            </p>
           

            
            <input type="submit" value="Submit"/>
        </form:form>  
        
             

</body>
</html>



























