<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <script defer src="/js/register.js"></script>
</head>
<body>
    <h2>Регистрация</h2>
    <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <label>Никнейм:</label><br>
        <input type="text" id="nickname" name="nickname" value="${param.nickname}"><br>
        <div id="nicknameError"></div>
        <label>Почта:</label><br>
        <input type="email" id="email" name="email" value="${param.email}"><br>
        <div id="emailError"></div>
        <label>Пароль:</label><br>
        <input type="password" id="password1" name="password"><br>
        <div id="password1Error"></div>
        <label>Повторите пароль:</label><br>
        <input type="password" id="password2" name="password2">
        <div id="password2Error"></div>
        <button type="submit">Зарегистрироваться</button>
        <div id="errorField">${error}</div>
    </form>
</body>
</html>