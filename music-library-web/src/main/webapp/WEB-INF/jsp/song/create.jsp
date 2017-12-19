<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="songs.create.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<form:form method="post" action="${pageContext.request.contextPath}/song/create"
			modelAttribute="songCreate" cssClass="form-horizontal">
		<div class="form-group">
			<form:label path="musicianId" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.musician"/>
			</form:label>
			<div class="col-sm-10">
				<form:select path="musicianId" cssClass="form-control">
					<c:forEach items="${musicians}" var="m">
						<form:option value="${m.id}">${m.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="musicianId" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<form:label path="genreId" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.genre"/>
			</form:label>
			<div class="col-sm-10">
				<form:select path="genreId" cssClass="form-control">
					<c:forEach items="${genres}" var="g">
						<form:option value="${g.id}">${g.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="genreId" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<form:label path="albumId" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.album"/>
			</form:label>
			<div class="col-sm-10">
				<form:select path="albumId" cssClass="form-control">
					<c:forEach items="${albums}" var="a">
						<form:option value="${a.id}">${a.title}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="albumId" cssClass="error"/>
			</div>
		</div>
		<div class="form-group ${title_error ? 'has-error' : ''}">
			<form:label path="title" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.title"/>
			</form:label>
			<div class="col-sm-10">
				<form:input path="title" cssClass="form-control"/>
				<form:errors path="title" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group ${bitrate_error ? 'has-error' : ''}">
			<form:label path="bitrate" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.bitrate"/>
			</form:label>
			<div class="col-sm-10">
				<form:input path="bitrate" cssClass="form-control"/>
				<form:errors path="bitrate" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group ${position_error ? 'has-error' : ''}">
			<form:label path="position" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.position"/>
			</form:label>
			<div class="col-sm-10">
				<form:input path="position" cssClass="form-control"/>
				<form:errors path="position" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group ${commentary_error ? 'has-error' : ''}">
			<form:label path="commentary" cssClass="col-sm-2 control-label">
				<fmt:message key="songs.create.song.commentary"/>
			</form:label>
			<div class="col-sm-10">
				<form:input path="commentary" cssClass="form-control"/>
				<form:errors path="commentary" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit">
					<fmt:message key="songs.create.save"/>
				</button>
			</div>
		</div>
	</form:form>
</jsp:attribute>
</my:pagetemplate>
