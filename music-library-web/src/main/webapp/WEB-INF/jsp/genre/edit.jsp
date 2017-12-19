<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="genreEdit.title" var="title"/>
<my:pagetemplate title="${title} ${genre.id}">
<jsp:attribute name="pageHeader">
	<div class="page-header">
		<div class="pull-right">
			<my:a href="/genre/detail/${genre.id}" class="btn btn-default">
				<fmt:message key="musicians.edit.backToDetail"/>
			</my:a>
		</div>
		<h1>
			<my:a href="/genre/list"><fmt:message key="navigation.genres"/></my:a> /
			<c:out value="${genre.name}"/>
		</h1>
	</div>
</jsp:attribute>
<jsp:attribute name="body">
	<form:form method="post" action="${pageContext.request.contextPath}/genre/edit"
			modelAttribute="genreUpdate" cssClass="form-horizontal">
		<div class="form-group ${name_error?'has-error':''}">
			<form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="genre.name"/></form:label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" cssClass="help-block"/>
			</div>
		</div>
		<form:hidden path="id"/>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit"><fmt:message key="genreUpdate.save"/></button>
			</div>
		</div>
	</form:form>

</jsp:attribute>
</my:pagetemplate>
