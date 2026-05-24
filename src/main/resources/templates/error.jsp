<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>

<div class="error-box">
    <h1>${title}</h1>
        <div class="message">
            ${message}
        </div>
    <a href="/register">Back to registration</a>
</div>
</body>
</html>