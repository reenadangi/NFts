<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:partial>

	

	<form:form action="/arts/create" method="post" modelAttribute="newArt" enctype="multipart/form-data" class="text-white" >
	<h3>Upload a New NFT Art</h3>
	    <div class="form-group" >
	        <form:label path="artName">Art Name:</form:label>
	        <form:errors class="text-danger" path="artName"/>
	        <form:input class="form-control" path="artName"/>
	    </div>
	    <div class="form-group">
	        <form:label path="description">Art Description:</form:label>
	        <form:errors class="text-danger" path="description"/>
	        <form:textarea class="form-control" path="description"/>
	    </div>
	    <div class="form-group">
	    <br />
	     <form:label path="description">Art Image</form:label>
	       <input type="file" name="pic" id="fileToUpload" >
	     </div>
	      <!-- Hidden Input for userId -->
	      <form:input type="hidden" value="${userId}" path="artist"/>
	      <hr />
	   
	    <input type="submit" class="btn btn-primary" value="Submit"/>
	</form:form>    
</t:partial>
