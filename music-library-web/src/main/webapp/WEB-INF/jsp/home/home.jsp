<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:pagetemplate>
<jsp:attribute name="body">

	<div class="jumbotron">
		<h1><fmt:message key="homepage.home.title"/></h1>
		<p class="lead"><fmt:message key="homepage.home.search.text"/></p>
		<form class="form-inline" method="get" action="${pageContext.request.contextPath}/search/result">
			<div class="form-group form-group-lg">
				<input type="text" name="searchTerm" class="form-control" required/>
			</div>
			<button type="submit" class="btn btn-primary btn-lg">
				<fmt:message key="homepage.home.search.button"/>
			</button>
		</form>
	</div>

	<h2><fmt:message key="homepage.home.albumsFromLastMonth"/></h2>

	<div class="row">
		<c:forEach items="${albumsFromLastMonth}" var="album">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/search/albumImage/${album.id}" alt="${fn:escapeXml(album.title)}">
					<div class="caption">
						<h3><c:out value="${album.title}"/></h3>
						<p><c:out value="${album.commentary}"/></p>
						<p>
							<fmt:message key="homepage.home.album.releaseDate"/>:
							<fmt:formatDate pattern="dd. MM. yyyy" value="${album.releaseDate}"/>
						</p>
						<p>
							<my:a href="/album/detail/${album.id}" class="btn btn-primary" role="button">
								<fmt:message key="homepage.home.album.detail"/>
							</my:a>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</jsp:attribute>
</my:pagetemplate>
