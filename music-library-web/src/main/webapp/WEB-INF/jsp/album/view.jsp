<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="albumView.title" var="title"/>
<my:pagetemplate title="${title}">
	<jsp:attribute name="pageHeader">
	<div class="page-header">
		<form method="post" action="${pageContext.request.contextPath}/album/delete/${album.id}" class="pull-right">
			<my:a href="/album/update/${album.id}" class="btn btn-default"><fmt:message key="album.edit"/></my:a>
			<button type="submit" class="btn btn-danger"><fmt:message key="album.delete"/></button>
		</form>
		<h1>
			<my:a href="/album/list"><fmt:message key="navigation.admin.albums"/></my:a> /
			<c:out value="${album.name}"/>
		</h1>
	</div>
	</jsp:attribute>
	<jsp:attribute name="body">
		<table class="table">
			<thead>
			<tr>
				<th><fmt:message key="album.id"/></th>
				<th><fmt:message key="album.name"/></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${album.id}</td>
				<td><c:out value="${album.name}"/></td>
			</tr>
			</tbody>
		</table>

	</jsp:attribute>
</my:pagetemplate>
