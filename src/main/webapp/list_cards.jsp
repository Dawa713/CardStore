<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="jspf/header.jspf" %>

<c:if test="${not empty sessionScope.flashSuccess}">
  <div class="container">
    <div class="alert alert-success" role="alert">
      ${sessionScope.flashSuccess}
    </div>
    <c:remove var="flashSuccess" scope="session"/>
  </div>
</c:if>
<c:if test="${not empty sessionScope.flashError}">
  <div class="container">
    <div class="alert alert-danger" role="alert">
      ${sessionScope.flashError}
    </div>
    <c:remove var="flashError" scope="session"/>
  </div>
</c:if>

<div class="container mt-4">
  <h1>Cards</h1>
  <a href="${pageContext.request.contextPath}/cards?action=new"
     class="btn btn-success mb-3">New Card</a>

  <table class="table table-striped">
    <thead>
      <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="c" items="${cards}">
        <tr>
          <td>
            <img src="${pageContext.request.contextPath}/images/${c.image}"
                 alt="${c.name}"
                 style="max-height:60px;"
                 onerror="this.src='${pageContext.request.contextPath}/images/default_card.png';"/>
          </td>
          <td>${c.name}</td>
          <td>
            <a href="${pageContext.request.contextPath}/cards?action=view&id=${c.id}"
               class="btn btn-info btn-sm">View</a>
            <a href="${pageContext.request.contextPath}/cards?action=edit&id=${c.id}"
               class="btn btn-warning btn-sm">Edit</a>
            <a href="${pageContext.request.contextPath}/cards?action=delete&id=${c.id}"
               class="btn btn-danger btn-sm"
               onclick="return confirm('Delete this card?');">
              Delete
            </a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="jspf/footer.jspf" %>
