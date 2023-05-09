<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/js/all.min.js"
	integrity="sha512-2bMhOkE/ACz21dJT8zBOMgMecNxx0d37NND803ExktKiKdSzdwn+L7i9fdccw/3V06gM/DBWKbYmQvKMdAA9Nw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<style>
body {
	margin-top: 20px;
	margin-right: 50px;
	margin-left: 20px;
}
</style>
</head>
<body>
	
		<%@ include file="navbar.jsp"%>
	<% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %>


	<div class="d-flex flex-row-reverse">

		<div class="p-2">
			<a href="AuthController?action=logout" class="btn btn-secondary">Déconnexion</a>
		</div>

		<div class="p-2">
			<h3>
				Bonjour ${username} 
			</h3>
		</div>


	</div>




	<h1>
		<strong>Liste des livres</strong>
	</h1>
	<form method="post" action="BibliothéqueController"
		class="form-inline my-2 my-lg-0">
		<input class="form-control mr-sm-2" type="text"
			placeholder="Recherche par titre" aria-label="Search" name="mc">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
			name="action" value="rechercher">
			<i class="fa fa-search"></i>
		</button>
	</form>
	<br>
	<table class="table table-striped">

		<tr>
			<th>ID</th>
			<th>ISBN</th>
			<th>Titre</th>
			<th>Auteur</th>
			<th>Editeur</th>
			<th>Image</th>
			<th>Genre</th>
			<th>Action</th>


		</tr>
		<c:forEach items="${livres}" var="l">
			<tr>
				<td>${l.idLivre}</td>
				<td>${l.ISBN}</td>
				<td>${l.titre}</td>
				<td>${l.auteur.nom}</td>
				<td>${l.editeur.nom}</td>
				<td><img src="<c:url value='images/${l.image}'/>" width="100"
					height="100" /></td>
				<td><c:forEach items="${l.genres}" var="genre">${genre.nom} <br>
					</c:forEach></td>
				<c:if test="${role == 'admin'}">
					<td><a
						href="BibliothéqueController?action=supprimer&id=${l.idLivre}"
						onclick="return confirm('Are you sure you want to delete this item?');">
							<span class="fa fa-trash-alt btn btn-danger"></span>
					</a> <a href="BibliothéqueController?action=modifier&id=${l.idLivre}">
							<span class="fa fa-edit btn btn-success"></span>
					</a></td>
				</c:if>
				<c:if test="${role == 'user'}">
					<td>
						<form action="addEmprunt" method="post" class="form-inline">
							<div class="form-group mx-sm-3 mb-2">


								<a
									href="BibliothéqueController?action=ajouterEmprunt&id=${l.idLivre}&idUser=${idUser}">
									Emprunter </a>

							</div>


						</form>

					</td>
				</c:if>


			</tr>
		</c:forEach>



	</table>

	<div class="d-flex justify-content-center mt-3">
    <ul class="pagination">
        <c:forEach var="pageNumber" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${pageNumber eq currentPage}">
                    <li class="page-item active">
                        <span class="page-link">${pageNumber}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="?page=${pageNumber}">${pageNumber}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>



</body>
</html>