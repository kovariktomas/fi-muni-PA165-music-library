<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="songs.list.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<p>
		<my:a href="/song/create" class="btn btn-success">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			<fmt:message key="songs.list.create"/>
		</my:a>
	</p>

	<table class="table table-striped table-align-middle">
		<thead>
			<tr>
				<th class="col-xs-1 col-md-1 col-lg-1"><fmt:message key="songs.list.song.id"/></th>
				<th><fmt:message key="songs.list.song.title"/></th>
				<th><fmt:message key="songs.list.song.musician"/></th>
				<th><fmt:message key="songs.list.song.genre"/></th>
				<th><fmt:message key="songs.list.song.album"/></th>
				<th><fmt:message key="songs.list.song.bitrate"/></th>
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
					<td>
						<my:a href="/album/detail/${song.album.id}">
							<c:out value="${song.album.title}"/>
						</my:a>
					</td>
					<td><c:out value="${song.bitrate}"/></td>
					<td>
						<form method="post" action="${pageContext.request.contextPath}/song/delete/${song.id}">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="songs.list.delete"/>
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</jsp:attribute>
</my:pagetemplate>
