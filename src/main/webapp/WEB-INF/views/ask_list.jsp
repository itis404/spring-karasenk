<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <a href="${pageContext.request.contextPath}/asks/create"> Создать аск </a>
</div>
<c:forEach var="ask" items="${asks.content}">
    <a href="${pageContext.request.contextPath}/asks/${ask.uniqueName}">
        <div>
            <p> ${ask.name} </p>
            <img class="icon" src=${pageContext.request.contextPath}/img/${ask.getIcon()}>
        </div>
    </a>
</c:forEach>

<div>
    <c:if test="${currentPage > 0}">
        <a href="?page=${currentPage - 1}">←</a>
    </c:if>

    <span>Страница ${currentPage + 1}</span>

    <c:if test="${asks.hasNext()}">
        <a href="?page=${currentPage + 1}">→</a>
    </c:if>
</div>