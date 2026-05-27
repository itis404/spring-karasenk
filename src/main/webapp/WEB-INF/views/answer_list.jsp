<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="answer" items="${answers.content}">
    <div style="border:1px solid #ccc; margin:10px 0; padding:10px;">
        <a href="${pageContext.request.contextPath}/personage/${answer.personageUniqueName}">
            ${answer.personageUniqueName}
        </a>
        <p>${answer.text}</p>
        <c:if test="${not empty answer.imageUrls}">
            <div>
                <c:forEach var="image" items="${answer.imageUrls}">
                    <img src="${image}" width="200">
                </c:forEach>
            </div>
        </c:if>
        <small>${answer.postTime}</small>
    </div>
    <hr>
</c:forEach>

<div>
    <c:if test="${currentPage > 0}">
        <a href="?page=${currentPage - 1}">←</a>
    </c:if>

    <span>Страница ${currentPage + 1}</span>

    <c:if test="${answers.hasNext()}">
        <a href="?page=${currentPage + 1}">→</a>
    </c:if>
</div>