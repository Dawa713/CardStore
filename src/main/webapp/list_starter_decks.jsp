<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>

<c:if test="${not empty sessionScope.flashError}">
  <div class="container">
    <div class="alert alert-danger">${sessionScope.flashError}</div>
    <c:remove var="flashError" scope="session"/>
  </div>
</c:if>

<div class="container mt-4">
  <h1>Starter Decks</h1>
  <a href="${pageContext.request.contextPath}/starter-decks?action=new" class="btn btn-success mb-3">New Deck</a>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Image</th><th>Name</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="d" items="${decks}">
        <tr>
          <td>
            <img src="${pageContext.request.contextPath}/images/${d.image}"
                 alt="${d.name}" style="max-height:60px;"
                 onerror="this.src='${pageContext.request.contextPath}/images/default_deck.png';"/>
          </td>
          <td>${d.name}</td>

          <td>
            <a href="${pageContext.request.contextPath}/starter-decks?action=view&id=${d.id}" class="btn btn-info btn-sm">View</a>
            <a href="${pageContext.request.contextPath}/starter-decks?action=edit&id=${d.id}" class="btn btn-warning btn-sm">Edit</a>
            <a href="${pageContext.request.contextPath}/starter-decks?action=delete&id=${d.id}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this deck?');">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="jspf/footer.jspf" %>
