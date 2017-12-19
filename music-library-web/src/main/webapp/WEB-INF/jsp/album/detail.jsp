<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${album.title}">
	<jsp:attribute name="pageHeader">
	<div class="page-header">
		<form method="post" action="${pageContext.request.contextPath}/album/delete/${album.id}" class="pull-right">
			<my:a href="/album/edit/${album.id}" class="btn btn-default">
				<fmt:message key="album.edit"/>
			</my:a>
			<button type="submit" class="btn btn-danger">
				<fmt:message key="album.delete"/>
			</button>
		</form>
		<h1>
			<my:a href="/album/list"><fmt:message key="album.detail.albums"/></my:a> /
			<c:out value="${album.title}"/>
		</h1>
	</div>
	</jsp:attribute>
	<jsp:attribute name="body">
		<c:if test="${not empty album.commentary}">
			<p>
				<c:out value="${album.commentary}"/>
			</p>
		</c:if>

		<h2><fmt:message key="albums.detail.songs"/></h2>

		<c:choose>
			<c:when test="${not empty songs}">
				<table class="table table-striped table-align-middle">
					<thead>
						<tr>
							<th class="col-xs-1 col-md-1 col-lg-1"><fmt:message key="albums.detail.song.id"/></th>
							<th><fmt:message key="albums.detail.song.title"/></th>
							<th><fmt:message key="albums.detail.song.musician"/></th>
							<th><fmt:message key="albums.detail.song.genre"/></th>
							<th><fmt:message key="albums.detail.song.bitrate"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${songs}" var="song">
							<tr>
								<td>${song.id}</td>
								<td><c:out value="${song.title}"/></td>
								<td>
									<my:a href="/musician/detail/${song.musician.id}" class="btn">
										<c:out value="${song.musician.name}"/>
									</my:a>
								</td>
								<td>
									<my:a href="/genre/view/${song.genre.id}" class="btn">
										<c:out value="${song.genre.name}"/>
									</my:a>
								</td>
								<td><c:out value="${song.bitrate}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p><fmt:message key="albums.detail.noSongs"/></p>
				<p>
					<my:a href="/song/create?albumId=${album.id}" class="btn btn-success">
						<fmt:message key="albums.detail.createFirstSong"/>
					</my:a>
				</p>
			</c:otherwise>
		</c:choose>
	</jsp:attribute>
</my:pagetemplate>
