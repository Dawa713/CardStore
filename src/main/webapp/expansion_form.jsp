<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<h1>
  <c:choose>
    <c:when test="${empty expansion.id}">New Expansion</c:when>
    <c:otherwise>Edit Expansion</c:otherwise>
  </c:choose>
</h1>

<form method="post" action="${pageContext.request.contextPath}/expansions">
  <c:choose>
    <c:when test="${empty expansion.id}">
      <input type="hidden" name="action" value="create"/>
    </c:when>
    <c:otherwise>
      <input type="hidden" name="action" value="update"/>
      <input type="hidden" name="id"     value="${expansion.id}"/>
    </c:otherwise>
  </c:choose>

  <div class="form-group">
    <label for="name">Name</label>
    <input id="name" name="name" class="form-control"
           value="${expansion.name}" required/>
  </div>
  <div class="form-group">
    <label for="releaseDate">Release Date</label>
    <input id="releaseDate" name="releaseDate" type="date"
           class="form-control"
           value="<fmt:formatDate value='${expansion.releaseDate}' pattern='yyyy-MM-dd'/>"
           required/>
  </div>
  <div class="form-group form-check">
    <input id="discontinued" name="discontinued" type="checkbox"
           class="form-check-input"
           ${expansion.discontinued ? 'checked':''}/>
    <label for="discontinued" class="form-check-label">Discontinued</label>
  </div>
  <div class="form-group">
    <label for="price">Price (€)</label>
    <input id="price" name="price" type="number" step="0.01"
           class="form-control"
           value="${expansion.price}"/>
  <div class="form-group">
     <label for="image">Image Filename</label>
     <input id="image"
            name="image"
            class="form-control"
            value="${not empty expansion.image ? expansion.image : 'default_card.png'}"/>
   </div>

  <button type="submit" class="btn btn-primary">
    <c:choose>
      <c:when test="${empty expansion.id}">Create</c:when>
      <c:otherwise>Update</c:otherwise>
    </c:choose>
  </button>
  <a href="${pageContext.request.contextPath}/expansions" class="btn btn-secondary">
    Cancel
  </a>
</form>

<%@ include file="jspf/footer.jspf" %>
