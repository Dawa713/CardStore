<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<c:choose>
  <c:when test="${not empty player}">
    <h1>Detalle de Usuario: ${player.username}</h1>
    <ul class="list-group">
      <li class="list-group-item"><strong>ID:</strong> ${player.id}</li>
      <li class="list-group-item"><strong>Username:</strong> ${player.username}</li>
      <li class="list-group-item"><strong>Password:</strong> ${player.password}</li>
      <li class="list-group-item"><strong>Email:</strong> ${player.email}</li>
      <li class="list-group-item"><strong>Rol:</strong> ${player.role}</li>
      <li class="list-group-item">
        <strong>Último Inicio:</strong>
        <fmt:formatDate value="${player.lastLogin}"
                        pattern="dd/MM/yyyy HH:mm:ss" />
      </li>
    </ul>
  </c:when>
  <c:otherwise>
    <div class="alert alert-warning">Usuario no encontrado.</div>
  </c:otherwise>
</c:choose>

<p class="mt-3">
  <a href="${pageContext.request.contextPath}/players" class="btn btn-secondary">
    Volver al listado de usuarios
  </a>
</p>
<p class="mt-3">
<a href="${pageContext.request.contextPath}/" class="btn btn-secondary">
    Volver al inicio
  </a>
</p>
<%@ include file="jspf/footer.jspf" %>
