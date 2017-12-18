<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message key="login.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<form:form method="post" action="${pageContext.request.contextPath}/login/"
			modelAttribute="userLogin" cssClass="form-horizontal">
		<div class="form-group ${mail_error ? 'has-error' : ''}">
			<form:label path="email" cssClass="col-sm-2 control-label">
				<fmt:message key="login.email"/>:
			</form:label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" cssClass="help-block"/>
			</div>
		</div>
		<div class="form-group ${password_error ? 'has-error' : ''}">
			<form:label path="passHash" cssClass="col-sm-2 control-label">
				<fmt:message key="login.password"/>:
			</form:label>
			<div class="col-sm-10">
				<form:password path="passHash" cssClass="form-control"/>
				<form:errors path="passHash" cssClass="help-block"/>
			</div>
		</div>
		<c:if test="${not empty alert_warning}">
			<p class="text-warning"><c:out value="${alert_warning}"></c:out></p>
		</c:if>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit"><fmt:message key="login.submit"/></button>
			</div>
		</div>
	</form:form>
</jsp:attribute>
</my:pagetemplate>
