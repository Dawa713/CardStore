<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<div class="container mt-5">
  <h1>Results</h1>

  <c:if test="${not empty cards}">
    <h2>Cards</h2>
    <ul>
      <c:forEach var="c" items="${cards}">
        <li>
          <a href="${pageContext.request.contextPath}/cards?action=view&id=${c.id}">
            ${c.name}
          </a>
           — ${c.price}€
        </li>
      </c:forEach>
    </ul>
  </c:if>

  <c:if test="${not empty expansions}">
    <h2>Expansions</h2>
    <ul>
      <c:forEach var="e" items="${expansions}">
        <li>
          <a href="${pageContext.request.contextPath}/expansions?action=view&id=${e.id}">
            ${e.name}
          </a>
           — ${e.price}€
        </li>
      </c:forEach>
    </ul>
  </c:if>

  <c:if test="${not empty decks}">
    <h2>Decks</h2>
    <ul>
      <c:forEach var="d" items="${decks}">
        <li>
          <a href="${pageContext.request.contextPath}/starter-decks?action=view&id=${d.id}">
            ${d.name}
          </a>
           — ${d.price}€
        </li>
      </c:forEach>
    </ul>
  </c:if>

  <p>
    <a href="${pageContext.request.contextPath}/search" class="btn btn-secondary">
      New Search
    </a>
  </p>
</div>

<%@ include file="jspf/footer.jspf" %>
