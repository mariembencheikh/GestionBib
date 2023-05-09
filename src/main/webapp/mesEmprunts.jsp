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
		<strong>Mes emrpunts</strong>
	</h1>
	
	<br>
	<table class="table table-striped">

		<tr>
			<th>ID</th>
						<th>Livre</th>
			
			<th>Date de l'emprunt</th>
			<th>Date de retour</th>
			


		</tr>
		<c:forEach items="${emprunts}" var="l">
			<tr>
				<td>${l.id}</td>
				<td>${l.livre.titre}</td>
				<td>${l.dateEmprunt}</td>
				<td>${l.dateRetour}</td>
				


			</tr>
		</c:forEach>



	</table>

	
</body>
</html>