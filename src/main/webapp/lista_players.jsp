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
  <h1>Players</h1>
  <a href="${pageContext.request.contextPath}/players?action=new" class="btn btn-success mb-3">New Player</a>
  <table class="table table-striped">
    <thead class="thead-dark">
      <tr>
        <th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Last Login</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="pl" items="${players}">
        <tr>
          <td>${pl.id}</td>
          <td>${pl.username}</td>
          <td>${pl.email}</td>
          <td>${pl.role}</td>
          <td><fmt:formatDate value="${pl.lastLogin}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
          <td>
            <a href="${pageContext.request.contextPath}/players?action=view&id=${pl.id}" class="btn btn-info btn-sm">View</a>
            <a href="${pageContext.request.contextPath}/players?action=edit&id=${pl.id}" class="btn btn-warning btn-sm">Edit</a>
            <a href="${pageContext.request.contextPath}/players?action=delete&id=${pl.id}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this player?');">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="jspf/footer.jspf" %>
