<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="genreView.title" var="title"/>
<my:pagetemplate title="${title}">
	<jsp:attribute name="pageHeader">
	<div class="page-header">
		<form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}" class="pull-right">
			<my:a href="/genre/update/${genre.id}" class="btn btn-default"><fmt:message key="genre.edit"/></my:a>
			<button type="submit" class="btn btn-danger"><fmt:message key="genre.delete"/></button>
		</form>
		<h1>
			<my:a href="/genre/list"><fmt:message key="navigation.genres"/></my:a> /
			<c:out value="${genre.name}"/>
		</h1>
	</div>
	</jsp:attribute>
	<jsp:attribute name="body">		
		<table class="table">
			<thead>
			<tr>
				<th><fmt:message key="genre.id"/></th>
				<th><fmt:message key="genre.name"/></th>
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
