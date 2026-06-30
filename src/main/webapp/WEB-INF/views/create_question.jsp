<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<form method="post" action="${pageContext.request.contextPath}/personages/${personage.uniqueName}/questions">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <textarea name="text" rows="5" cols="60" maxlength="128" required></textarea>
    <br>
    <button type="submit"> Спросить </button>
</form>