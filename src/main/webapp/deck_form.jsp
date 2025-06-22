<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<h1>
  <c:choose>
    <c:when test="${empty deck.id}">Nuevo Starter Deck</c:when>
    <c:otherwise>Editar Starter Deck</c:otherwise>
  </c:choose>
</h1>

<form method="post"
      action="${pageContext.request.contextPath}/starter-decks"
      enctype="multipart/form-data">
  <c:choose>
    <c:when test="${empty deck.id}">
      <input type="hidden" name="action" value="create"/>
    </c:when>
    <c:otherwise>
      <input type="hidden" name="action" value="update"/>
      <input type="hidden" name="id"     value="${deck.id}"/>
    </c:otherwise>
  </c:choose>

  <!-- Campos normales -->
  <div class="form-group">
    <label for="name">Nombre</label>
    <input id="name" name="name" class="form-control"
           value="${deck.name}" required/>
  </div>
  <div class="form-group">
    <label for="releaseDate">Fecha de Lanzamiento</label>
    <input id="releaseDate" name="releaseDate" type="date"
           class="form-control"
           value="<fmt:formatDate value='${deck.releaseDate}' pattern='yyyy-MM-dd'/>"
           required/>
  </div>
  <div class="form-group form-check">
    <input id="discontinued" name="discontinued" type="checkbox"
           class="form-check-input"
           ${deck.discontinued ? 'checked':''}/>
    <label for="discontinued" class="form-check-label">Descontinuado</label>
  </div>
  <div class="form-group">
    <label for="price">Precio (€)</label>
    <input id="price" name="price" type="number" step="0.01"
           class="form-control" value="${deck.price}"/>
  </div>

  <!-- NUEVO campo de subida de imagen -->
  <div class="form-group">
    <label for="imageFile">Imagen (jpg/png)</label>
    <input id="imageFile" name="imageFile" type="file"
           accept="image/*" class="form-control"/>
    <c:if test="${not empty deck.image}">
      <!-- guardamos vieja imagen en hidden -->
      <input type="hidden" name="existingImage" value="${deck.image}"/>
      <p>Imagen actual:</p>
      <img src="${pageContext.request.contextPath}/images/${deck.image}"
           alt="thumb" style="max-width:150px;"/>
    </c:if>
  </div>

  <button type="submit" class="btn btn-primary">
    <c:choose>
      <c:when test="${empty deck.id}">Crear</c:when>
      <c:otherwise>Actualizar</c:otherwise>
    </c:choose>
  </button>
  <a href="${pageContext.request.contextPath}/starter-decks"
     class="btn btn-secondary">Cancelar</a>
</form>

<%@ include file="jspf/footer.jspf" %>
