<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial scale=1.0" />
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Accueil</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<h1 align="center" >Liste des enchères</h1>
	<div class="contnair-fluid" >
		<form action="" method="POST">
			<label>filtres :</label>
			<p>${coche }</p>
			<br>
			<br>
			<br>
			<div class="col-1">
				<input type="search" name="recherche" placeholder="le nom de l'article contient " size=50 spellcheck=false >
				<br>
				<p>${ancienCat }
			    <select name="categorie" id="categorie">
					<c:forEach items="${ categories }" var="categorie" varStatus="status">
					
					<c:choose>
						<c:when test="${categorie.noCategorie == ancienCat }">
							<option value=${ categorie.noCategorie } selected>${categorie.libelle }</option>>
						</c:when>
						<c:otherwise>
							<option value=${ categorie.noCategorie }>${categorie.libelle }</ option>
						</c:otherwise>
					</c:choose>
					
					</c:forEach>
			    </select>
		    </div>
		    <c:if test="${!empty sessionId}">
		    	<div class="col-1" >
		    		<input type="radio" name="achatVente" value="achats">
		    		<label>Mes achats</label>
		    		<div align="left">
		    			<input type="checkbox" name="encheresOuvertes" value="encheresOuvertes">
		    			<label>Enchères ouvertes</label><br>
		    			<input type="checkbox" name="encheresUtilisateur" value="encheresUtilisateur">
		    			<label>Mes enchères</label><br>
		    			<input type="checkbox" name="encheresRemportees" value="encheresRemportees">
		    			<label>Enchères remportées</label><br>
		    		</div>
		    	</div >
		    	<div class="col-1" >
		    		<input type="radio" name="achatVente" value="ventes">
		    		<label>Mes ventes</label><br>
		    		<div >
			    		<input type="checkbox" name="ventesEnCours" value="ventesEnCours">
		    			<label>Mes ventes en cours</label><br>
		    			<input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees">
		    			<label>Ventes non debuté</label><br>
		    			<input type="checkbox" name="ventesTerminees" value="ventesTerminees">
		    			<label>Ventes terminé</label>
	    			</div>
		    	</div>
		    </c:if>
		    <div class= "col-2">
		    	<input type="submit" value="rechercher">
		    </div>
		</form>
	</div>
	<div>
		<c:forEach items="${ articles }" var="article" varStatus="status">
				<div>					
					<div>
						<img src="https://via.placeholder.com/150">
					</div>
					<div>
						<a href="#">${article.getNomArticle()}</a><br>
						<p>Prix : ${article.getPrixArticle()}</p><br>
						<p>Fin de l'enchère ${article.getDateFinEncheres()}</p><br>
						<p>Vendeur : <a href="#">${article.getUtilisateur().getPseudo}</a><br>
					</div>					
				</div>    	
		</c:forEach>
	</div>

</body>
</html>