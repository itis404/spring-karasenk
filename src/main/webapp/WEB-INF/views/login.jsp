<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
    <h2>Вход</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <label>Никнейм:</label><br>
        <input type="text" id="nickname" name="username"><br>
        <label>Пароль:</label><br>
        <input type="password" id="password" name="password"><br>
        <button type="submit">Зарегистрироваться</button>
        <div id="errorField">${error}</div>
    </form>
</body>
</html>