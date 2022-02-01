<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:partial>

		<h5>Edit art - ${editArt.artName}</h5>
		<p>
			<a href="/arts">art Dashboard</a> <a href="/logout">Logout</a>
		</p>
		<form:form action="/arts/update/${editArt.id}" method="patch"
			modelAttribute="editArt">
	
			<div class="form-group">
				<form:label path="artName">art Name:</form:label>
				<form:errors class="text-danger" path="artName" />
				<form:input class="form-control" path="artName"
					value="${editArt.artName}" />
			</div>
			<div class="form-group">
				<form:label path="description">Description</form:label>
				<form:errors class="text-danger" path="description" />
				<form:input class="form-control" path="description"
					value="${editArt.description}" />

			</div>
			<form:hidden path="artist" value="${art.artist}"/>
			<input type="submit" class="btn btn-primary" value="Submit" />
		</form:form>

</t:partial>


</body>
</html>