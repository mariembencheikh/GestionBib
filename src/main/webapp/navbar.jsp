<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
 

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
      <li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController">Liste des livres <span class="sr-only"></span></a>
      </li>
      
      	<c:if test="${role == 'admin'}">
		
	
		<li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController?action=ajout">Ajouter livre <span class="sr-only"></span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController?action=listeAuteurs">Auteurs <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController?action=listeEditeurs">Editeurs <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController?action=listeGenres">Genres <span class="sr-only"></span></a>
      </li>
	
		</c:if>
	      	<c:if test="${role == 'user'}">
	
      <li class="nav-item active">
        <a class="nav-link" href="BibliothéqueController?action=mesEmprunts&userId=${idUser}">Mes emprunts <span class="sr-only"></span></a>
      </li>
      		</c:if>
      
      </ul>
    
  </div>
</nav>
</html>