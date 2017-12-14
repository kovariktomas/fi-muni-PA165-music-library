<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

	<div class="jumbotron">
		<h1><f:message key="welcome.title"/></h1>
		<p class="lead"><f:message key="welcome.text"/></p>
		<form class="form-inline" method="get" action="${pageContext.request.contextPath}/search/result">
			<div class="form-group form-group-lg">
				<input pattern=".{3,}"   required title="Minimum 3 char!"type="text" name="searchTerm" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary btn-lg"><fmt:message key="welcome.search"/></button>
			<div class="help-block">Minimum 3 char</div>
		</form>
	</div>

</jsp:attribute>
</my:pagetemplate>
