<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${ask.name}</h1>

<c:if test="${not empty ask.icon}">
    <img class="icon" src="${pageContext.request.contextPath}/img/${ask.icon}">
</c:if>
<hr>
<jsp:include page="/WEB-INF/views/ask_sidebar.jsp"/>
<jsp:include page="/WEB-INF/views/answer_list.jsp"/>
