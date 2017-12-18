<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="albumCreate.title" var="title"/>
<my:pagetemplate title="${title}">
	<jsp:attribute name="body">
		<form:form cssClass="editable-form" method="POST" action="${pageContext.request.contextPath}/album/create" modelAttribute="albumCreate">
			<table>
				<tr>
					<th><form:label path="title"><fmt:message key="album.name"/>:</form:label></th>
					<td><form:input path="title"/></td>
					<td><form:errors path="title" cssClass="error"/></td>
				</tr>
				<tr>
					<th><form:label path="releaseDate"><fmt:message key="album.releaseDate"/> <c:out value="(dd-mm-yyyy):"/></form:label></th>
					<td><form:input path="releaseDate"/></td>
					<td><form:errors path="releaseDate" cssClass="error"/></td>
				</tr>
				<tr>

					<th><form:label path="albumArt"><fmt:message key="album.albumArt"/>:</form:label></th>
					<td><form:input type = "file" path="albumArt"/></td>
					<td><form:errors path="albumArt" cssClass="error"/></td>
				</tr>
				<tr>
					<th><form:label path="commentary"><fmt:message key="album.commentary"/>:</form:label></th>
					<td><form:textarea path="commentary"/></td>
					<td><form:errors path="commentary" cssClass="error"/></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${album.id}"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit"><fmt:message key="albumCreate.createNew"/></button>
		</form:form>
	</jsp:attribute>
</my:pagetemplate>
