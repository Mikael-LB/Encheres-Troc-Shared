<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial scale=1.0" />
<link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet" >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Accueil</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<h1 align="center" >Liste des enchères</h1>
	<div class="contnair-fluid" >
		<form action="" method="POST">
			<div  align="center">
				<input type="search" name="recherche" placeholder="le nom de l'article contient " size=50 spellcheck=false >
				<br>
				<label>Catégorie :</label>
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
		    </div >
		    <div class="row" align="center">
			    <c:if test="${!empty sessionId}">
			    	<div class="col " align="right" >
			    		<input type="radio" name="achatVente" value="achats" id="achats" onchange="achat();">
			    		<label>Mes achats</label>
			    		<div>
			    			<input  type="checkbox" name="encheresOuvertes" id="encheresOuvertes" value="encheresOuvertes" disabled="disabled">
			    			<label>Enchères ouvertes</label><br>
			    			<input  type="checkbox" name="encheresUtilisateur" id="encheresUtilisateur" value="encheresUtilisateur" disabled="disabled">
			    			<label>Mes enchères</label><br>
			    			<input  type="checkbox" name="encheresRemportees" id="encheresRemportees" value="encheresRemportees" disabled="disabled">
			    			<label>Enchères remportées</label><br>
			    		</div>
			    	</div >
			    	<div class="col" align="left">
			    		<input type="radio" name="achatVente" value="ventes" id="ventes" onchange="vente();">
			    		<label>Mes ventes</label><br>
			    		<div >
				    		<input class="vente" type="checkbox" name="ventesEnCours" id="ventesEnCours" value="ventesEnCours" disabled="disabled" >
			    			<label>Mes ventes en cours</label><br>
			    			<input class="vente" type="checkbox" name="ventesNonDebutees" id="ventesNonDebutees" value="ventesNonDebutees" disabled="disabled">
			    			<label>Ventes non debuté</label><br>
			    			<input class="vente" type="checkbox" name="ventesTerminees" id="ventesTerminees" value="ventesTerminees" disabled="disabled">
			    			<label>Ventes terminé</label>
		    			</div>
			    	</div>
			    </c:if>
		    </div>
		    <div align="center">
		    	<input type="submit" value="rechercher">
		    </div>
		</form>
	</div>
	<br>
	<div class="card-group mb-4">
		<c:forEach items="${ articles }" var="article" varStatus="status">
				<div class="card flex-row flex-wrap"	>
					<div  class="card-header border-0">					
						<img class="card-img-left" src="https://via.placeholder.com/150">
					</div>
					<div class="card-block px-2">
						<a class="card-title" href="#">${article.getNomArticle()}</a><br>
						<p class="card-body">	
								Prix : ${article.getPrixArticle()}<br>
								Fin de l'enchère : ${article.getDateFinEncheres()}<br>
								Vendeur : <a href="<c:url value="/Profil?user=${article.getUtilisateur().getNumeroUtilisateur()}"/>">${article.getUtilisateur().getPseudo()}</a>
						</p>
					</div>			
				</div>    	
		</c:forEach>
	</div>
	<script>
	function achat(){
		var ach = document.getElementById("achats");
		
	    if(ach.checked){
	      	document.getElementById("encheresOuvertes").disabled = false;
	    	document.getElementById("encheresUtilisateur").disabled = false;
	    	document.getElementById("encheresRemportees").disabled = false;
	    	document.getElementById("ventesEnCours").disabled = true;
    		document.getElementById("ventesNonDebutees").disabled = true;
    		document.getElementById("ventesTerminees").disabled = true;
	    	}
	    	else{
	    		document.getElementById("encheresOuvertes").disabled = true;
	    		document.getElementById("encheresUtilisateur").disabled = true;
	    		document.getElementById("encheresRemportees").disabled = true;
	    		document.getElementById("ventesEnCours").disabled = false;
		    	document.getElementById("ventesNonDebutees").disabled = false;
		    	document.getElementById("ventesTerminees").disabled = false;}
	    
	}
	function vente(){
		var ven = document.getElementById("ventes");
		
	    if(ven.checked){
	    	document.getElementById("encheresOuvertes").disabled = true;
    		document.getElementById("encheresUtilisateur").disabled = true;
    		document.getElementById("encheresRemportees").disabled = true;
    		document.getElementById("ventesEnCours").disabled = false;
	    	document.getElementById("ventesNonDebutees").disabled = false;
	    	document.getElementById("ventesTerminees").disabled = false;}
	    
	    	else{
	    		document.getElementById("encheresOuvertes").disabled = false;
	    		document.getElementById("encheresUtilisateur").disabled = false;
	    		document.getElementById("encheresRemportees").disabled = false;
	    		document.getElementById("ventesEnCours").disabled = true;
	    		document.getElementById("ventesNonDebutees").disabled = true;
	    		document.getElementById("ventesTerminees").disabled = true;}
	    		
	    
	}
	</script>
</body>
</html>