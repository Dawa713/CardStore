<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="jspf/header.jspf" %>

<div class="container mt-4">
  <h1>
    <c:choose>
      <c:when test="${empty card}">New Card</c:when>
      <c:otherwise>Edit Card</c:otherwise>
    </c:choose>
  </h1>

  <form method="post"
        action="${pageContext.request.contextPath}/cards"
        enctype="multipart/form-data">
    <c:choose>
      <c:when test="${empty card}">
        <input type="hidden" name="action" value="create"/>
      </c:when>
      <c:otherwise>
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${card.id}"/>
        <input type="hidden" name="existingImage" value="${card.image}"/>
      </c:otherwise>
    </c:choose>

    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <input id="name" name="name" class="form-control"
             value="${card.name}" required/>
    </div>

    <div class="mb-3">
      <label for="releaseDate" class="form-label">Release Date</label>
      <input id="releaseDate" name="releaseDate" type="date"
             class="form-control" value="${card.releaseDate}" required/>
    </div>

    <div class="mb-3">
      <label for="attack" class="form-label">Attack</label>
      <input id="attack" name="attack" type="number"
             class="form-control" value="${card.attack}" required/>
    </div>

    <div class="mb-3">
      <label for="defense" class="form-label">Defense</label>
      <input id="defense" name="defense" type="number"
             class="form-control" value="${card.defense}" required/>
    </div>

    <div class="mb-3">
      <label for="price" class="form-label">Price (€)</label>
      <input id="price" name="price" type="number" step="0.01"
             class="form-control" value="${card.price}" required/>
    </div>

    <div class="mb-3">
      <label for="imageFile" class="form-label">Image File</label>
      <input id="imageFile" name="imageFile" type="file"
             accept="image/*" class="form-control"/>
      <c:if test="${not empty card.image}">
        <small class="form-text text-muted">
          Current: ${card.image}
        </small>
      </c:if>
    </div>

    <button type="submit" class="btn btn-primary">
      <c:choose>
        <c:when test="${empty card}">Create</c:when>
        <c:otherwise>Update</c:otherwise>
      </c:choose>
    </button>
    <a href="${pageContext.request.contextPath}/cards?action=list"
       class="btn btn-secondary">Cancel</a>
  </form>
</div>

<%@ include file="jspf/footer.jspf" %>
