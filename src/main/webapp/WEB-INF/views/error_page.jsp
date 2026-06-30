<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div>
<c:choose>
    <c:when test="${not empty errorMessage}">
        <div class="text"> ${errorMessage} </div>
    </c:when>
    <c:otherwise>
        <h1> Произошла какая-то ошибка </h1>
        <div> Попробуйте ещё раз, вдруг получится </div>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>