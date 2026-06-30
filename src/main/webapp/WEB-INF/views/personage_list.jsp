<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <a href="${pageContext.request.contextPath}/asks/${askUrl}/personages/create"> Новый персонаж </a>
</div
<c:forEach var="personage" items="${personages.content}">
    <div>
        <a href="${pageContext.request.contextPath}/personages/${personage.uniqueName}">
            <p> ${personage.name} </p>
            <img class="icon" src=${pageContext.request.contextPath}/img/${personage.icon}>
        </a>
    </div>
</c:forEach>

<div>
    <c:if test="${currentPage > 0}">
        <a href="?page=${currentPage - 1}">←</a>
    </c:if>

    <span>Страница ${currentPage + 1}</span>

    <c:if test="${personages.hasNext()}">
        <a href="?page=${currentPage + 1}">→</a>
    </c:if>
</div>