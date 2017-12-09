<%-- 
    Document   : list
    Created on : 9.12.2017, 17:40:09
    Author     : Kovarik Tomas
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Upravit žánr s id ${genre.id}">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/genre/saveUpdate"
               modelAttribute="genreUpdate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Název žánru</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
		<form:hidden path="id" />
        <button class="btn btn-primary" type="submit">Upravit žánr</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>