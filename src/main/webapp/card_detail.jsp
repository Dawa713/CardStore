<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <%@ include file="jspf/header.jspf" %>

  <fmt:setLocale value="es_ES"/>
  <div class="container mt-4">
  <c:choose>
    <c:when test="${not empty card}">
      <h1>${card.name}</h1>
      <img src="${pageContext.request.contextPath}/images/${card.image}"
           style="max-width:200px;"
           onerror="this.src='${pageContext.request.contextPath}/images/default_card.png';"/>
      <ul class="list-group mt-3">
        <li class="list-group-item"><strong>ID:</strong> ${card.id}</li>
        <li class="list-group-item"><strong>Release Date:</strong> ${card.releaseDate}</li>
        <li class="list-group-item"><strong>Attack:</strong> ${card.attack}</li>
        <li class="list-group-item"><strong>Defense:</strong> ${card.defense}</li>
        <li class="list-group-item"><strong>Foil:</strong> ${card.foil ? 'Yes' : 'No'}</li>
        <li class="list-group-item">
          <strong>Price:</strong>

          <fmt:formatNumber value="${card.price}" type="currency" currencySymbol="€" maxFractionDigits="2"/>
        </li>
      </ul>
    </c:when>
    <c:otherwise>
      <div class="alert alert-warning">Card not found.</div>
    </c:otherwise>
  </c:choose>



<p><a href="${pageContext.request.contextPath}/cards" class="btn btn-secondary">Back</a></p>
</div>
<%@ include file="jspf/footer.jspf" %>