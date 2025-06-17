<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="jspf/header.jspf" %>


<h1>Registro de carta</h1>
<form method="post">
  <c:if test="${not empty carta}">
    <input type="hidden" name="id" value="${carta.id}"/>
  </c:if>
  Nombre: <input name="name" value="${carta.name}"/><br/>
  Fecha:  <input type="date" name="releaseDate" value="${carta.releaseDate}"/><br/>
  Ataque:<input name="attack" value="${carta.attack}"/><br/>
  Defensa:<input name="defense" value="${carta.defense}"/><br/>
  Foil:  <input type="checkbox" name="foil" <c:if test="${carta.foil}">checked</c:if> /><br/>
  Precio:<input type="number" step="0.01" name="price" value="${carta.price}"/><br/>
  Imagen:<input name="image" value="${carta.image}"/><br/>
  <button type="submit">
    <c:choose>
      <c:when test="${empty carta}">Crear</c:when>
      <c:otherwise>Actualizar</c:otherwise>
    </c:choose>
  </button>
</form>
<p><a href="${pageContext.request.contextPath}/">Volver al inicio</a></p>

<%@ include file="jspf/footer.jspf" %>