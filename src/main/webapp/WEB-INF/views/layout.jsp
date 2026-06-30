<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/sidebar.jsp"/>

    <div class="content">
        <jsp:include page="${contentPage}"/>
    </div>
</body>
</html>