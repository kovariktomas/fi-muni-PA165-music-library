<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:pagetemplate title="${genre.name}">
	<jsp:attribute name="pageHeader">
	<div class="page-header">
		<form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}" class="pull-right">
			<my:a href="/genre/edit/${genre.id}" class="btn btn-default"><fmt:message key="genres.detail.edit"/></my:a>
			<button type="submit" class="btn btn-danger"><fmt:message key="genres.detail.delete"/></button>
		</form>
		<h1>
			<my:a href="/genre/list"><fmt:message key="genres.detail.genres"/></my:a> /
			<c:out value="${genre.name}"/>
		</h1>
	</div>
	</jsp:attribute>
	<jsp:attribute name="body">		
		<h2>
			<fmt:message key="genres.detail.albums"/>
		</h2>
		<c:choose>
			<c:when test="${not empty albums}">
				<div class="row">
					<c:forEach items="${albums}" var="album">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<img src="${pageContext.request.contextPath}/search/albumImage/${album.id}"
									 alt="${fn:escapeXml(album.title)}">
								<div class="caption">
									<h3><c:out value="${album.title}"/></h3>
									<p><c:out value="${album.commentary}"/></p>
									<p>
										<fmt:message key="genres.detail.album.releaseDate"/>:
										<fmt:formatDate pattern="dd. MM. yyyy" value="${album.releaseDate}"/>
									</p>
									<p>
										<my:a href="/album/detail/${album.id}" class="btn btn-primary" role="button">
											<fmt:message key="genres.detail.album.detail"/>
										</my:a>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p><fmt:message key="genres.detail.noAlbums"/></p>
				<p>
					<my:a href="/album/create?genreId=${genre.id}" class="btn btn-success">
						<fmt:message key="genres.detail.createFirstAlbum"/>
					</my:a>
				</p>
			</c:otherwise>
		</c:choose>
	</jsp:attribute>
</my:pagetemplate>
