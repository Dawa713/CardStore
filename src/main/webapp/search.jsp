<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="jspf/header.jspf" %>

<div class="container mt-5">
  <h1>Search</h1>
  <form id="searchForm" method="post" action="${pageContext.request.contextPath}/search" novalidate>
    <div class="mb-3">
      <label for="term" class="form-label">Keyword</label>
      <input id="term"
             name="term"
             type="text"
             class="form-control"
             required
             placeholder="Enter keyword…">
    </div>
    <div class="mb-3">
      <label for="maxPrice" class="form-label">Max Price (€)</label>
      <input id="maxPrice"
             name="maxPrice"
             type="number"
             step="0.01"
             class="form-control"
             required
             placeholder="e.g. 100.00">
    </div>
    <div class="mb-3">
      <label class="form-label">Types</label><br/>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" name="type" id="chkCards" value="cards" checked>
        <label class="form-check-label" for="chkCards">Cards</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" name="type" id="chkExp" value="expansions">
        <label class="form-check-label" for="chkExp">Expansions</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" name="type" id="chkDecks" value="decks">
        <label class="form-check-label" for="chkDecks">Starter Decks</label>
      </div>
    </div>
    <button id="searchBtn" type="submit" class="btn btn-primary" disabled>Search</button>
  </form>
</div>

<script>
// Tomamos referencias a los elementos
const form     = document.getElementById('searchForm');
const term     = document.getElementById('term');
const maxPrice = document.getElementById('maxPrice');
const btn      = document.getElementById('searchBtn');

// Función que habilita/deshabilita el botón
function toggleButton() {
  const ok = term.value.trim() !== '' && maxPrice.value.trim() !== '';
  btn.disabled = !ok;
}

// Escuchamos cambios en ambos inputs
term.addEventListener('input', toggleButton);
maxPrice.addEventListener('input', toggleButton);

// (Opcional) Previene el envío si los campos siguen vacíos
form.addEventListener('submit', function(e) {
  if (term.value.trim() === '' || maxPrice.value.trim() === '') {
    e.preventDefault();
  }
});
</script>

<%@ include file="jspf/footer.jspf" %>
