<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%> <%@ taglib prefix="t"
tagdir="/WEB-INF/tags" %>
<t:partial>
  <table class="table table-dark">
    <thead>
      <tr>
        <th>Art name</th>
        <th>Artist</th>
        <th>Avg Ratings</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${arts}" var="art">
        <tr>
          <td>
            <a href="/arts/art/${art.id}">${art.artName}"</a>
          </td>
          <td>${art.artist.firstName}</td>
          <td>${art.avgRating}</td>

          <td></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</t:partial>
