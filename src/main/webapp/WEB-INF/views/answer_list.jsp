<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="answer" items="${answers.content}">
    <div style="border:1px solid #ccc; margin:10px 0; padding:10px;">
        <c:if test="${ask.isAdmin}">
            <small>
                <form action="${pageContext.request.contextPath}/asks/${askUrl}/answers/${answer.id}/delete"
                        onsubmit="return confirm('Удалить ответ?')" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit">Удалить</button>
                </form>
            </small>
        </c:if>
        <a href="${pageContext.request.contextPath}/personages/${answer.personageUniqueName}">
            ${answer.personageUniqueName}
        </a>
        <p class="text">${answer.text}</p>
        <c:if test="${not empty answer.images}">
            <div>
                <c:forEach var="image" items="${answer.images}">
                    <img src="<c:url value='/img/${image}'/>" width="500">
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