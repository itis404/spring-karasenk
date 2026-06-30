<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="question" items="${questions.content}" >
    <div>
        <p>${question.nickname}:</p>
        <p class="text">${question.text}</p>
    </div>
    <br>
</c:forEach>
<jsp:include page="/WEB-INF/views/create_question.jsp"/>