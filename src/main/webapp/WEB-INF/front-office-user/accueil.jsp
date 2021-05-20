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
	<div class="container" >
	
	<h1 class="my-4 text-center" >Liste des enchères</h1>
		<form action="" method="POST">
			<div  class="text-center mb-4">
				<input class ="mb-2" type="search" name="recherche" placeholder="le nom de l'article contient" value="${param.recherche }" size=50 spellcheck=false >
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
		    <div class="row mb-2 justify-content-center">
			    <c:if test="${!empty sessionId}">
			    	

			    	<div class="col-lg-2 col-sm-2 text-align-left" >	    	
			    		<input type="radio" name="achatVente" value="achats" id="achats" onchange="achat();" ${param.achatVente== "achats" ? "checked" : "" }>
			    		<label>Mes achats</label>
			    		<div>
			    			<input  type="checkbox" name="encheresOuvertes" id="encheresOuvertes" value="encheresOuvertes" ${param.achatVente== "achats" ? "" : "disabled" } ${!empty param.encheresOuvertes ? "checked" : "" }>
			    			<label>Enchères ouvertes</label><br>
			    			<input  type="checkbox" name="encheresUtilisateur" id="encheresUtilisateur" value="encheresUtilisateur" ${param.achatVente== "achats" ? "" : "disabled" } ${!empty param.encheresUtilisateur ? "checked" : "" }>
			    			<label>Mes enchères</label><br>
			    			<input  type="checkbox" name="encheresRemportees" id="encheresRemportees" value="encheresRemportees" ${param.achatVente== "achats" ? "" : "disabled" } ${!empty param.encheresRemportees ? "checked" : "" }>
			    			<label>Enchères remportées</label><br>
			    		</div>
			    	</div >
			    	
			    	<div class="col-lg-2 col-sm-2 text-align-left">
			    		<input type="radio" name="achatVente" value="ventes" id="ventes" onchange="vente();" ${param.achatVente== "ventes" ? "checked" : "" }>
			    		<label>Mes ventes</label><br>
			    		<div >
				    		<input class="vente" type="checkbox" name="ventesEnCours" id="ventesEnCours" value="ventesEnCours" ${param.achatVente== "ventes" ? "" : "disabled" } ${!empty param.ventesEnCours ? "checked" : "" }>
			    			<label>Mes ventes en cours</label><br>
			    			<input class="vente" type="checkbox" name="ventesNonDebutees" id="ventesNonDebutees" value="ventesNonDebutees" ${param.achatVente== "ventes" ? "" : "disabled" } ${!empty param.ventesNonDebutees ? "checked" : "" } >
			    			<label>Ventes non debuté</label><br>
			    			<input class="vente" type="checkbox" name="ventesTerminees" id="ventesTerminees" value="ventesTerminees" ${param.achatVente== "ventes" ? "" : "disabled" } ${!empty param.ventesTerminees ? "checked" : "" } >
			    			<label>Ventes terminé</label>
		    			</div>
			    	</div>
			    	
			    </c:if>
		    </div>
		    <div align="center">
		    	<input class="btn btn-primary" type="submit" value="rechercher">
		    </div>
		</form>
	
	<br>
 
  	<div class="card-deck d-flex justify-content-center flex-wrap">

			<c:forEach items="${ articles }" var="article" varStatus="status">
					<div class="card d-flex m-2">
						<div class="card-header border-0">
							<img class="card-img-left" src="https://via.placeholder.com/150">
						</div>
						<div class="card-block px-2">
							<a class="card-title" href="<c:url value='/Page-Article?article=${article.getNoArticle()}'/>">${article.getNomArticle()}</a><br>
							<p class="card-body">
								Prix : ${article.getPrixArticle()}<br> Fin de l'enchère :
								${article.getDateFinEncheres()}<br> Vendeur : <a
									href="<c:url value='/Profil?user=${article.getUtilisateur().getNumeroUtilisateur()}'/>">${article.getUtilisateur().getPseudo()}</a>
							</p>
						</div>
					</div>
			</c:forEach>
	</div> 
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