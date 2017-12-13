<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Songs">
<jsp:attribute name="body">

    <my:a href="/song/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Song
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>musician</th>
            <th>genre</th>
            <th>album</th>
            <th>title</th>
            <th>bitrate</th>
            <th>position</th>
            <th>commentary</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${songs}" var="song">
            <tr>
                <td>${song.id}</td>
                <td><c:out value="${song.musician.name}"/></td>
                <td><c:out value="${song.genre.name}"/></td>
                <td><c:out value="${song.album.title}"/></td>
                <td>${song.title}</td>
                <td>${song.bitrate}</td>
                <td>${song.position}</td>
                <td>${song.commentary}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/song/delete/${song.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>