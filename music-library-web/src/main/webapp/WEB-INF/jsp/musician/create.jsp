<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="musicians.create.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<form:form method="post" action="${pageContext.request.contextPath}/musician/create"
			   modelAttribute="musicianCreate" cssClass="form-horizontal">
		<div class="form-group ${name_error ? 'has-error' : ''}">
			<form:label path="name" cssClass="col-sm-2 control-label">
				<fmt:message key="musicians.create.name"/>
			</form:label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit"><fmt:message key="musicians.create.save"/></button>
			</div>
		</div>
	</form:form>
</jsp:attribute>
</my:pagetemplate>
