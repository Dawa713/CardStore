<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<c:choose>
  <c:when test="${not empty expansion}">
    <h1>Expansion: ${expansion.name}</h1>
    <div class="mb-3">
          <img src="${pageContext.request.contextPath}/images/${expansion.image}"
               alt="${expansion.name}"
               style="max-width:300px;"
               onerror="this.src='${pageContext.request.contextPath}/images/default_expansion.png';" />
        </div>
    <ul class="list-group mt-3">
      <li class="list-group-item"><strong>ID:</strong> ${expansion.id}</li>
      <li class="list-group-item">
        <strong>Release Date:</strong>
        <fmt:formatDate value="${expansion.releaseDate}" pattern="yyyy-MM-dd"/>
      </li>
      <li class="list-group-item">
        <strong>Discontinued:</strong> ${expansion.discontinued ? 'Yes':'No'}
      </li>
      <li class="list-group-item"><strong>Price:</strong> ${expansion.price} €</li>
      <li class="list-group-item"><strong>Image:</strong> ${expansion.image}</li>
    </ul>
  </c:when>
  <c:otherwise>
    <div class="alert alert-warning">Expansion not found.</div>
  </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/expansions"
      class="btn btn-secondary">Back to list</a></p>
<%@ include file="jspf/footer.jspf" %>
