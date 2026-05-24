<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <script> src="${pageContext.request.contextPath}/js/register.js"</script>
</head>
<body>
    <h2>Регистрация</h2>
    <form>
        <label>Никнейм:</label><br>
        <input type="text" id="nickname" name="nickname" value="${param.nickname}"><br>
        <label>Почта:</label><br>
        <input type="email" id="email" name="email" value="${param.email}"><br>
        <label>Пароль:</label><br>
        <input type="password" id="password1" name="password1" value="${param.password1}"><br>
        <label>Повторите пароль:</label><br>
        <input type="password" id="password2" name="password2" value="${param.password2}">
    </form>
</body>
</html>