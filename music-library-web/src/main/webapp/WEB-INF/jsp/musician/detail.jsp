<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${musician.name}">
<jsp:attribute name="pageHeader">
	<div class="page-header">
		<form method="post" action="${pageContext.request.contextPath}/musician/delete/${musician.id}" class="pull-right">
			<my:a href="/musician/edit/${musician.id}" class="btn btn-default">
				<fmt:message key="musicians.detail.edit"/>
			</my:a>
			<button type="submit" class="btn btn-danger">
				<fmt:message key="musicians.detail.delete"/>
			</button>
		</form>
		<h1>
			<my:a href="/musician/list"><fmt:message key="musicians.detail.musicians"/></my:a> /
			<c:out value="${musician.name}"/>
		</h1>
	</div>
</jsp:attribute>
<jsp:attribute name="body">
	<h2>
		<fmt:message key="musicians.detail.albums"/>
	</h2>
	<c:choose>
		<c:when test="${not empty albums}">
			<table class="table">
				<thead>
					<tr>
						<th><fmt:message key="musicians.detail.album.id"/></th>
						<th><fmt:message key="musicians.detail.album.name"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${albums}" var="album">
						<tr>
							<td><c:out value="${album.id}"/></td>
							<td><c:out value="${album.title}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p><fmt:message key="musicians.detail.noAlbums"/></p>
			<p>
				<my:a href="/album/create?musicianId=${musician.id}" class="btn btn-success">
					<fmt:message key="musicians.detail.createFirstAlbum"/>
				</my:a>
			</p>
		</c:otherwise>
	</c:choose>
</jsp:attribute>
</my:pagetemplate>
