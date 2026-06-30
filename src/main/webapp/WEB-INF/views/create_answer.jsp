<%@ page contentType="text/html;charset=UTF-8" %>
<script defer src="${pageContext.request.contextPath}/js/create_answer.js"></script>

<h1>Добавить ответ</h1>

<form id="answerForm" action="${pageContext.request.contextPath}/personages/${personageUniqueName}/new-answer" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div>
        <input type="text" id="text" name="text" maxlength="256" required>
    </div>

    <div id="file-container">
        <input type="file" name="images" accept="image/*"/>
    </div>
    <button type="button" onclick="addFileInput()"> Добавить картинку </button>
    <button type="submit">Сохранить</button>
</form>
<div>${error}</div>