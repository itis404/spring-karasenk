<%@ page contentType="text/html;charset=UTF-8" %>
<script defer src="${pageContext.request.contextPath}/js/create_personage.js"></script>
<h1>Добавить персонажа</h1>

<form id="personageForm" action="${pageContext.request.contextPath}/asks/${askUrl}/personages/create" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div>
        <label for="name">Имя</label>
        <input type="text" id="name" name="name" maxlength="64" required>
        <div id="nameError"></div>
    </div>
    <div>
        <label for="uniqueName">Короткое (уникальное) имя</label>
        <input type="text" id="uniqueName" name="uniqueName" maxlength="64" required>
        <div id="uniqueNameError"></div>
    </div>
    <div>
        <label for="info">Инфо</label><br>
        <textarea id="info" name="info" rows="10" cols="50" maxlength="4096"></textarea>
    </div>
    <div>
        <label>Аватарка</label>
        <input type="file" name="icon" accept="image/*">
    </div>
    <button type="submit">Сохранить</button>
</form>
<div>${error}</div>