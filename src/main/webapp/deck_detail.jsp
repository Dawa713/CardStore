<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<div class="container mt-4">
  <c:choose>
    <c:when test="${not empty deck}">
      <h1>Starter Deck: ${deck.name}</h1>
      <img src="${pageContext.request.contextPath}/images/${deck.image}"
           alt="${deck.name}" style="max-width:300px;"
           onerror="this.src='${pageContext.request.contextPath}/images/default_deck.png';"/>
      <ul class="list-group mt-3">
        <li class="list-group-item"><strong>ID:</strong> ${deck.id}</li>
        <li class="list-group-item">
          <strong>Release Date:</strong>
          <fmt:formatDate value="${deck.releaseDate}" pattern="yyyy-MM-dd"/>
        </li>
        <li class="list-group-item">
          <strong>Discontinued:</strong> ${deck.discontinued ? 'Yes' : 'No'}
        </li>
        <li class="list-group-item"><strong>Price:</strong> ${deck.price} €</li>
      </ul>
    </c:when>
    <c:otherwise>
      <div class="alert alert-warning">Deck not found.</div>
    </c:otherwise>
  </c:choose>

  <a href="${pageContext.request.contextPath}/starter-decks?action=list"
     class="btn btn-secondary mt-3">Back to list</a>
</div>

<%@ include file="jspf/footer.jspf" %>
