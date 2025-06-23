<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<div class="container mt-4">
<c:choose>
  <c:when test="${not empty player}">
    <h1>Player Detail: ${player.username}</h1>
    <ul class="list-group">
      <li class="list-group-item"><strong>ID:</strong> ${player.id}</li>
      <li class="list-group-item"><strong>Username:</strong> ${player.username}</li>
      <li class="list-group-item"><strong>Password:</strong> ${player.password}</li>
      <li class="list-group-item"><strong>Email:</strong> ${player.email}</li>
      <li class="list-group-item"><strong>Rol:</strong> ${player.role}</li>

      <li class="list-group-item">
          <strong>Competitive:</strong>
          <c:choose>
            <c:when test="${player.competitive}">Yes</c:when>
            <c:otherwise>No</c:otherwise>
          </c:choose>
        </li>

      <li class="list-group-item">
        <strong>Last Login:</strong>
        <fmt:formatDate value="${player.lastLogin}"
                        pattern="dd/MM/yyyy HH:mm:ss" />
      </li>
    </ul>
  </c:when>
  <c:otherwise>
    <div class="alert alert-warning">player not found.</div>
  </c:otherwise>
</c:choose>

<p class="mt-3">
  <a href="${pageContext.request.contextPath}/players" class="btn btn-secondary">
    Back to Player List
  </a>
</p>
</div></div>
<%@ include file="jspf/footer.jspf" %>
