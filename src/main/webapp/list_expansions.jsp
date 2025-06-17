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
  <h1>Expansions</h1>
  <a href="${pageContext.request.contextPath}/expansions?action=new" class="btn btn-success mb-3">New Expansion</a>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Image</th><th>Name</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="e" items="${expansions}">
        <tr>
          <td>
            <img src="${pageContext.request.contextPath}/images/${e.image}"
                 alt="${e.name}" style="max-height:60px;"
                 onerror="this.src='${pageContext.request.contextPath}/images/default_expansion.png';"/>
          </td>
          <td>${e.name}</td>

          <td>
            <a href="${pageContext.request.contextPath}/expansions?action=view&id=${e.id}" class="btn btn-info btn-sm">View</a>
            <a href="${pageContext.request.contextPath}/expansions?action=edit&id=${e.id}" class="btn btn-warning btn-sm">Edit</a>
            <a href="${pageContext.request.contextPath}/expansions?action=delete&id=${e.id}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this expansion?');">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="jspf/footer.jspf" %>
