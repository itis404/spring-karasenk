<%@ page contentType="text/html;charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/personages/${personage.uniqueName}/edit" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="hidden" name="uniqueName" value="${personage.uniqueName}">
    <div>
        <label for="name">Имя</label>
        <input type="text" id="name" name="name" maxlength="64" value="${personage.name}" required>
    </div>
    <div>
        <label for="info">Инфо</label><br>
        <textarea id="info" name="info" rows="10" cols="50" maxlength="4096" value="${personage.info}"></textarea>
    </div>
    <div>
        <label>Аватарка</label>
        <input type="file" name="icon" accept="image/*">
    </div>
    <button type="submit">Сохранить изменения</button>
</form>