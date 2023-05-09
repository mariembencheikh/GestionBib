<%@page import="javax.servlet.descriptor.TaglibDescriptor"  session="false"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/js/all.min.js" integrity="sha512-2bMhOkE/ACz21dJT8zBOMgMecNxx0d37NND803ExktKiKdSzdwn+L7i9fdccw/3V06gM/DBWKbYmQvKMdAA9Nw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="navbar.html" %>
<br>
<% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %>


<div class="container-fluid">
<div class="card mx-5" >
  <div class="card-header">
    <h3>Ajouter livre</h3>
  </div>
  <div class="card-body">
    <form  method="post" action="BibliothéqueController" enctype="multipart/form-data">
  <div class="form-group">
    <label for="titre">Titre</label>
    <input type="text" class="form-control" id="titre"  name="titre" value="${livre.titre}">
  </div>
  
  <div class="form-group">
    <label for="isbn">ISBN</label>
    <input type="text" class="form-control" id="isbn"  name="isbn" value="${livre.isbn}">
  </div>
    
   <div class="form-group">
    <label for="auteur">Auteur</label>
		<select class="form-select" aria-label="Default select example" name=auteur id="auteur" >
			
			<option selected=selected hidden="">Choisir un auteur</option>
		    <c:forEach items="${auteurs}" var="a">
		    <option value="${a.id}" 
		    	<c:if test="${a.id == livre.auteur.id}">
		    		 selected="selected"
		    	</c:if>
		    >
		    	${a.nom}
		    </option>
		    
		    </c:forEach>
		</select>  
	</div>
  <div class="form-group">
    <label for="editeur">Editeur</label>
		<select class="form-select" aria-label="Default select example" name=editeur id="editeur" >
			
			<option selected=selected hidden="">Choisir un éditeur</option>
		    <c:forEach items="${editeurs}" var="e">
		    <option value="${e.idEditeur}" 
		    	<c:if test="${e.idEditeur == livre.editeur.idEditeur}">
		    		 selected="selected"
		    	</c:if>
		    >
		    	${e.nom}
		    </option>
		    
		    </c:forEach>
		</select>  
	</div>
	<div class="form-group">
    <label for="image">Image</label>
    <input type="file" class="form-control" id="image"  name="image" value="${livre.image}">
  </div>
  <button type="submit" class="btn btn-primary" name="action" value="save">
		  <c:if test="${livre.idLivre==null}">
		 	 Ajouter
		  </c:if>
		  <c:if test="${livre.idLivre!=null}">
		 	 Modifier
		  </c:if>
	 </button>
  
       <input type="hidden" class="form-control"  name="id" value="${livre.idLivre }">
  
  
</form>
  </div>
</div>
</div>
</body>
</html>