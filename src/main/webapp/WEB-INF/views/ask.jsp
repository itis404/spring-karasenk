<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${ask.name}</h1>

<c:if test="${not empty ask.icon}">
    <img src="${pageContext.request.contextPath}/img/${ask.icon}" alt="${ask.name}" width="128">
</c:if>
<hr>
<h2>Ответы</h2>
<c:forEach var="answer" items="${ask.answers.content}">
    <div style="border:1px solid #ccc; margin:10px 0; padding:10px;">
        <h3>
            <a href="${pageContext.request.contextPath}/personage/${answer.personageUniqueName}">
                ${answer.personageUniqueName}
            </a>
        </h3>
        <p>${answer.text}</p>
        <small>${answer.postTime}</small>
        <c:if test="${not empty answer.images}">
            <div>
                <c:forEach var="image" items="${answer.images}">
                    <img src="${pageContext.request.contextPath}/img/${image}" alt="image" width="200">
                </c:forEach>
            </div>
        </c:if>
    </div>
</c:forEach>

<c:if test="${ask.answers.hasNext()}">
    <a href="?page=${param.page == null ? 1 : param.page + 1}">
        Следующая страница
    </a>
</c:if>
<div>
    <c:if test="${currentPage > 0}">
        <a href="?page=${currentPage - 1}">←</a>
    </c:if>

    <span>Страница ${currentPage + 1}</span>

    <c:if test="${answers.hasNext()}">
        <a href="?page=${currentPage + 1}">→</a>
    </c:if>
</div>
