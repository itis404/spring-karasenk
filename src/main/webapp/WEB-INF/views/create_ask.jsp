<script defer src="/js/create_ask.js"></script>
<form id="askForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/ask/create">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    <label> Короткое имя (уникальное) </label>
    <input type="text" id="uniqueName" name="uniqueName">
    <div id="uniqueNameError"></div><br>

    <label> Название </label>
    <input type="text" id="name" name="name">
    <div id="nameError"></div><br>

    <label>Аватарка</label>
    <input type="file" name="icon" accept="image/*"><br>
    <button type="submit">Создать</button>
</form>
<div>${errorField}</div>