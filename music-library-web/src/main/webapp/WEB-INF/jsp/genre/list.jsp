<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="genre.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

	<my:a href="/genre/new" class="btn btn-primary">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		<fmt:message key="genreCreate.title"/>
	</my:a>

	<table class="table">
		<thead>
		<tr>
			<th><fmt:message key="genre.id"/></th>
			<th><fmt:message key="genre.name"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${genres}" var="genre">
			<tr>
				<td>${genre.id}</td>
				<td><c:out value="${genre.name}"/></td>
				<td>
					<my:a href="/genre/view/${genre.id}" class="btn btn-primary">
						<fmt:message key="genre.detail"/>
					</my:a>
				</td>
				<td>
					<my:a href="/genre/update/${genre.id}" class="btn btn-primary">
						<fmt:message key="genre.edit"/>
					</my:a>
				</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
						<button type="submit" class="btn btn-primary"><fmt:message key="genre.delete"/></button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</jsp:attribute>
</my:pagetemplate>
