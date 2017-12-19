<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="navigation.genres" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

	<p>
		<my:a href="/genre/new" class="btn btn-success">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			<fmt:message key="genreCreate.title"/>
		</my:a>
	</p>

	<table class="table table-align-middle table-striped">
		<thead>
			<tr>
				<th class="col-xs-1 col-md-1 col-lg-1"><fmt:message key="genre.id"/></th>
				<th><fmt:message key="genre.name"/></th>
				<th colspan="2" class="col-xs-2 col-md-3 col-lg-2"><fmt:message key="genre.actions"/></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${genres}" var="genre">
			<tr>
				<td>${genre.id}</td>
				<td><my:a href="/genre/view/${genre.id}"><c:out value="${genre.name}"/></my:a></td>
				<td>
					<my:a href="/genre/update/${genre.id}" class="btn btn-default">
						<fmt:message key="genre.edit"/>
					</my:a>
				</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
						<button type="submit" class="btn btn-danger"><fmt:message key="genre.delete"/></button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</jsp:attribute>
</my:pagetemplate>
