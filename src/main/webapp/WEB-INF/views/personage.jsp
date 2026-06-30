<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <div>
        <a href="${pageContext.request.contextPath}/personages/${personage.uniqueName}/questions"> Вопросы </a>
    </div>
    <c:if test="${personage.isOwner}">
        <small>
            <a href="${pageContext.request.contextPath}/personages/${personage.uniqueName}/edit"> редактировать </a>
            <form action="${pageContext.request.contextPath}/personages/${personage.uniqueName}/delete"
                    onsubmit="return confirm('Удалить персонажа?')" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit">Удалить</button>
            </form>
            <a href="${pageContext.request.contextPath}/personages/${personage.uniqueName}/new-answer"> новый ответ </a>
        </small>
    </c:if>
    <h2> ${personage.name} </h2>
    <div class="text"> ${personage.info} </div>
    <img src="${pageContext.request.contextPath}/img/${personage.icon}" width="200"/>
</div>
<div>
    <jsp:include page="${personageContentPage}"/>
</div>