<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1.0" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="assets/css/encheres-troc-style.css"/>">
<title>${ titre }</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<div class="container">
	<br>
	<h1 align="center">${ titre }</h1>
	<p align="center">${message }</p>
	<br>
	<br>
	<div class="row">
		<div class="col-4">
			<img src="https://via.placeholder.com/150">
		</div>
		<div class="col-8">
			<h2 align="center">${ article.getNomArticle() }</h2>
			<br>
			<div class="row">
				<div class="row">
					<div class="col-4">
						<label>Description : </label>
					</div>
					<div class="col-8">
						<p>${ article.getDescription() }</p>
					</div>
				</div>
				<c:if test="${from=='aquisition'||from=='maVente'||from=='enCour'||from=='detail'}">
					<div class="row">	
						<div class="col-4">
							<label>Meilleur offre : </label>
						</div>
						<div class="col-8">
							${ article.getPrixArticle() } <%@ include file="credit.jsp" %> par : <a href="<c:url value='/Profil?user=${article.getMeilleurEnchere().getUtilisateur().getNumeroUtilisateur()}'/>">
							${ article.getMeilleurEnchere().getUtilisateur().getPseudo() }</a>
						</div>
					</div>
				</c:if> 
				<div class="row">
					<div class="col-4">
						<label>Mise à prix : </label>
					</div>
					<div class="col-8">
						${ article.getMiseAPrix() } <%@ include file="credit.jsp" %>
					</div>
				</div>
				<c:if test="${from=='miseEnVente'|| from=='maVente'|| from=='enCour' || from=='detail'}">
					<div class="row">
						<div class="col-4">
							<label>Date de début d'enchère : </label>
						</div>
						<div class="col-4">
							${article.getDateDebutEncheres()}
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label>Date de fin d'enchère : </label>
						</div>
						<div class="col-4">
							${article.getDateFinEncheres()}							
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-4">
						<label>Retrait : </label>
					</div>
					<div class="col-4">
						${ article.getPointRetrait().getRue() }<br>
						${ article.getPointRetrait().getCodePostal() } ${ article.getPointRetrait().getVille() }
					</div>
				</div>
				<div class="row">
					<div class="col-4">
						<label>Vendeur : </label>
					</div>
					<div class="col-4">
						<a href="<c:url value='/Profil?user=${article.getUtilisateur().getNumeroUtilisateur()}'/>">
						${article.getUtilisateur().getPseudo()}</a>
					</div> 
				</div>
				<c:if test="${from=='aquisition'}">
					<div class="row">
						<div class="col-4">
							<label>Téléphone : </label>
						</div>
						<div class="col-4">
							${ article.getUtilisateur().getTelephone() }
						</div>
					</div>
				</c:if> 		
			</div>
			<br>
			<c:if test="${from=='miseEnVente'||from=='detail'}" >
				<a href="<c:url value="/" />" > <input type="button" value="accueil"> </a>
			</c:if>
			<c:if test="${from=='aquisition'}">
				<a href="#" > <input type="button" value="retour"> </a>
			</c:if>
			<c:if test="${from=='maVente'}">
				<a href="#" > <input type="button" value="Retrait effectué"> </a>
			</c:if>
			<c:if test="${from=='enCour'}">
				<form action="Page-Article" method="POST">				
					<input type="hidden" name="noArticle" value="${article.getNoArticle()}"/>
					<label>Votre Proposition :</label>
					<input type="number" name="mise" min="${article.getPrixArticle()+1 }" max="${utilisateur.getCredit() }" value="${article.getPrixArticle()+1 }" ${article.getMeilleurEnchere().getUtilisateur().getNumeroUtilisateur()==utilisateur.getNumeroUtilisateur() ? "disabled" : "" }> 
					<input type="submit" name="encherir" value="Encherir" ${article.getMeilleurEnchere().getUtilisateur().getNumeroUtilisateur()==utilisateur.getNumeroUtilisateur() ? "disabled" : "" }>
				</form>
				<br>
				<a href="<c:url value="/" />" > <input type="button" value="Retour a l'accueil"> </a>
			</c:if>

			<c:if test="${!empty errorList}">
				<div class="form-group row">
					<div class="col-sm-12 col-form-label">
						<c:forEach var="error" items="${errorList }">
							<p class="text-danger">${messageReader.getMessageErreur(error)}</p>
						</c:forEach>
					</div>
				</div>
			</c:if>

		</div>
	</div>
	</div>
	<%@ include file="footer.jsp" %>	
</body>
</html>