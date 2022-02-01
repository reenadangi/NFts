<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<div class="container bg-dark">
	<h3 class="bg-dark text-white">
	<img src="/imgs/cat-logo.png" alt="" style="width:100px"/>
		Discover, collect, and sell extraordinary NFTs --
		<c:out value="${user.firstName}" />
	</h3>
	
	<a href="/arts">Collections | </a>
	<a href="/arts/new">Upload New Art | </a>
	<a href="/logout">Logout</a>
	<hr>
	<jsp:doBody/>
	<hr>
</div>
</body>
</html>
	