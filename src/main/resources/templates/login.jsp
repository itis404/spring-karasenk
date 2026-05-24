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
    <form>
        <label>Никнейм:</label><br>
        <input type="text" id="nickname" name="nickname" value="${param.nickname}"><br>

        <label>Пароль:</label><br>
        <input type="password" id="password" name="password" value="${param.password}"><br>
    </form>
</body>
</html>