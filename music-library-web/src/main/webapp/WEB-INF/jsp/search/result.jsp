<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="search.result.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<form class="form-inline" method="get" action="${pageContext.request.contextPath}/search/result">
		<div class="form-group form-group-lg">
			<input type="text" name="searchTerm" class="form-control" value="${fn:escapeXml(searchTerm)}" required/>
		</div>
		<button type="submit" class="btn btn-primary btn-lg"><fmt:message key="search.result.search"/></button>
	</form>
	<c:if test="${fn:length(albums) gt 0}">
		<div>
			<h3>
				<fmt:message key="search.result.albums"/>
			</h3>
			<div class="row">
				<c:forEach items="${albums}" var="album" begin="0" end="3">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/search/albumImage/${album.id}"
								alt="${fn:escapeXml(album.title)}">
							<div class="caption">
								<h3><c:out value="${album.title}"/></h3>
								<p><c:out value="${album.commentary}"/></p>
								<p>
									<fmt:message key="search.result.album.releaseDate"/>:
									<fmt:formatDate pattern="dd. MM. yyyy" value="${album.releaseDate}"/>
								</p>
								<p>
									<my:a href="/album/detail/${album.id}" class="btn btn-primary" role="button">
										<fmt:message key="search.result.album.detail"/>
									</my:a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<hr>
	</c:if>
	<c:if test="${fn:length(genres) gt 0}">
		<div>
			<h2>
				<fmt:message key="search.result.genres"/>
			</h2>
			<table class="table table-striped table-align-middle">
				<thead>
					<tr>
						<th><fmt:message key="search.result.genre.name"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${genres}" var="genre" begin="0" end="4">
						<tr>
							<td>
								<my:a href="/genre/detail/${genre.id}">
									<c:out value="${genre.name}"/>
								</my:a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
	</c:if>
	<c:if test="${fn:length(songs) gt 0}">
		<div>
			<h3>
				<fmt:message key="search.result.songs"/>
			</h3>
			<table class="table table-striped table-align-middle">
				<thead>
					<tr>
						<th class="col-xs-1 col-md-1 col-lg-1"><fmt:message key="search.result.song.id"/></th>
						<th><fmt:message key="search.result.song.title"/></th>
						<th><fmt:message key="search.result.song.musician"/></th>
						<th><fmt:message key="search.result.song.genre"/></th>
						<th><fmt:message key="search.result.song.bitrate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${songs}" var="song">
						<tr>
							<td>${song.id}</td>
							<td><c:out value="${song.title}"/></td>
							<td>
								<my:a href="/musician/detail/${song.musician.id}">
									<c:out value="${song.musician.name}"/>
								</my:a>
							</td>
							<td>
								<my:a href="/genre/detail/${song.genre.id}">
									<c:out value="${song.genre.name}"/>
								</my:a>
							</td>
							<td><c:out value="${song.bitrate}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
	</c:if>
	<c:if test="${fn:length(musicians) gt 0}">
		<div>
			<h3>
				<fmt:message key="search.result.musicians"/>
			</h3>
			<table class="table table-striped table-align-middle">
				<thead>
					<tr>
						<th><fmt:message key="search.result.musician.name"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${musicians}" var="musician" begin="0" end="4">
						<tr>
							<td>
								<my:a href="/musician/detail/${musician.id}">
									<c:out value="${musician.name}"/>
								</my:a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${(fn:length(albums) lt 1) and (fn:length(songs) lt 1) and (fn:length(musicians) lt 1) and (fn:length(genres) lt 1)}">
		<p>
			<div class="alert alert-info"><fmt:message key="search.result.noResult"/></div>
		</p>
	</c:if>
</jsp:attribute>
</my:pagetemplate>
