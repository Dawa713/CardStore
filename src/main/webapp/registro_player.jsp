<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="jspf/header.jspf" %>
<div class="container mt-4">
<h1>
  <c:choose>
    <c:when test="${empty player.id}">New Player</c:when>
    <c:otherwise>Edit Player</c:otherwise>
  </c:choose>
</h1>

<form method="post" action="${pageContext.request.contextPath}/players">
  <!-- Determinamos la acción -->
  <c:choose>
    <c:when test="${empty player.id}">
      <input type="hidden" name="action" value="create"/>
    </c:when>
    <c:otherwise>
      <input type="hidden" name="action" value="update"/>
      <input type="hidden" name="id"     value="${player.id}"/>
    </c:otherwise>
  </c:choose>


  <div class="form-group">
    <label for="username">Username</label>
    <input id="username" name="username" class="form-control"
           value="${player.username}" required />
  </div>


  <div class="form-group">
    <label for="password">Password</label>
    <input id="password" name="password" type="password"
           class="form-control" value="${player.password}" required />
  </div>


  <div class="form-group">
    <label for="email">Email</label>
    <input id="email" name="email" type="email"
           class="form-control" value="${player.email}" required />
  </div>

  <!-- Role -->
  <div class="form-group">
    <label for="role">Role</label>
    <select id="role" name="role" class="form-control">
      <option value="user"  ${player.role=='user'? 'selected':''}>User</option>
      <option value="admin" ${player.role=='admin'? 'selected':''}>Admin</option>
    </select>
  </div>

<div class="form-group form-check">
      <input id="competitive" name="competitive" type="checkbox"
             class="form-check-input"
             ${player.competitive ? 'checked':''}/>
      <label for="competitive" class="form-check-label">Competitive?</label>
    </div>



  <button type="submit" class="btn btn-primary">
    <c:choose>
      <c:when test="${empty player.id}">Create</c:when>
      <c:otherwise>Update</c:otherwise>
    </c:choose>
  </button>
  <a href="${pageContext.request.contextPath}/players"
     class="btn btn-secondary">Cancel</a>
</form>
</div>
<%@ include file="jspf/footer.jspf" %>