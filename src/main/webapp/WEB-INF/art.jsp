<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:partial>

 <div class="card">
            <div class="row">
                <div class="col-sm-5">
                    <img class="card-img" src="${art.artUrl}" alt="NFT Art">
                </div>
                <div class="col-sm-7">
                    <div class="card-body">
                        <h5 class="card-title"> ${art.artName} </h5>
                        <p class="card-text"><strong>Artist :</strong>   ${art.artist.firstName}</p>
                         <p class="card-text">${art.description}</p>
                       
                    </div>
                    	<hr>
                    <c:if test="${rating.ratedBy.id==userLoggedIn}">
						You have Rated this Art
					</c:if>
                    <h5 class="text-success">Leave a rating:</h5>
			     
			
					<c:forEach items="${art.ratings}" var="rating">
					Rated By: ${rating.ratedBy.firstName}
					Rating: ${rating.rating}
					
					
					<br>
				</c:forEach>
				<form:form action="/arts/rateArt" method="post"
				modelAttribute="newRating">
				<div class="form-group">
					<form:label path="rating">Rating:</form:label>
					<form:input class="form-control" path="rating" />
					<!-- Hidden Input for userId -->
					<form:input type="hidden" value="${userId}" path="ratedBy" />
					<!-- Hidden Input for artId -->
					<form:input type="hidden" value="${art.id}" path="artRated" />
					<br>
					<input type="submit" class="btn btn-primary" value="Rate"/>
				</div>
				<h2>
				Art ratings :
				</h2>
			</form:form>
					<hr>
                <c:if test="${art.artist.id==userLoggedIn}">
						<a href="/arts/delete/${art.id}" class="btn btn-danger"> Delete </a>
						<a href="/arts/edit/${art.id}" class="btn btn-secondary">Edit</a>
				</c:if>
                </div>
            </div>
        </div>


	<c:choose>
					 <c:when test="${art.ratings.contains(user)}">
						<h3>You have Rated this Art</h3>
					</c:when>
					<c:otherwise>
					<h3>You have Not Rated this Art</h3>
					</c:otherwise>
					</c:choose>		
	
	
</t:partial>