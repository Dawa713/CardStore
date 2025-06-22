<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="jspf/header.jspf" %>

<div class="container mt-5" style="max-width: 400px;">
  <h2 class="mb-4 text-center">Acceso de Usuario</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">
      ${error}
    </div>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/login">
    <div class="form-group mb-3">
      <label for="username">Usuario</label>
      <input id="username"
             name="username"
             type="text"
             class="form-control"
             placeholder="Introduce tu usuario"
             required autofocus />
    </div>

    <div class="form-group mb-4">
      <label for="password">Contraseña</label>
      <input id="password"
             name="password"
             type="password"
             class="form-control"
             placeholder="••••••••"
             required />
    </div>

    <button type="submit" class="btn btn-primary w-100">
      Entrar
    </button>
  </form>

  <p class="mt-3 text-center">

    <a href="${pageContext.request.contextPath}/registro"></a>
  </p>
</div>

<%@ include file="jspf/footer.jspf" %>
