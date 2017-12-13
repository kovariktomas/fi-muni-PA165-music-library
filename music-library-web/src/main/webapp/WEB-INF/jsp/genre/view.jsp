<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Detail Žánru">
<jsp:attribute name="body">		
	<table>
		<tr>
			<td>
				<form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
					<button type="submit" class="btn btn-primary">Smazat</button>
				</form>
			</td>
			<td>
				<my:a href="/genre/update/${genre.id}" class="btn btn-primary">Upravit</my:a>
			</td>
		</tr>
	</table>

	<table class="table">
		<thead>
		<tr>
			<th>id</th>
			<th>Název žánru</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${genre.id}</td>
			<td><c:out value="${genre.name}"/></td>
		</tr>
		</tbody>
	</table>

</jsp:attribute>
</my:pagetemplate>
