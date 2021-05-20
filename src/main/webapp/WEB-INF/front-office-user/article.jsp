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
			<table class="row">
				<tr>
					<td class="col-4">
						<label>Description : </label>
					</td>
					<td class="col-4">
						${ article.getDescription() }
					</td>
				</tr>
				<c:if test="${from=='aquisition'||from=='maVente'||from=='enCour'}">
					<tr>
						<td class="col-4">
							<label>Meilleur offre : </label>
						</td>
						<td class="col-4">
							${ article.getPrixArticle() } par : ${ article.getMeilleurEnchere().getUtilisateur().getPseudo() }
						</td>
					</tr>
				</c:if> 
				<tr>
					<td class="col-4">
						<label>Mise à prix : </label>
					</td>
					<td class="col-4">
						${ article.getMiseAPrix() }
					</td>
				</tr>
				<c:if test="${from=='miseEnVente'|| from=='maVente'|| from=='enCour' || from=='detail'}">
					<tr>
						<td class="col-4">
							<label>Date de début d'enchère : </label>
						</td>
						<td class="col-4">
							${article.getDateDebutEncheres()}
						</td>
					</tr>
					<tr>
						<td class="col-4">
							<label>Date de fin d'enchère : </label>
						</td>
						<td class="col-4">
							${article.getDateFinEncheres()}							
						</td>
					</tr>
				</c:if>  
				<tr>
					<td class="col-4">
						<label>Retrait : </label>
					</td>
					<td class="col-4">
						${ article.getPointRetrait().getRue() }<br>
						${ article.getPointRetrait().getCodePostal() } ${ article.getPointRetrait().getVille() }
					</td>
				</tr>
				<tr>
					<td class="col-4">
						<label>Vendeur : </label>
					</td>
					<td class="col-4">
						<a href="<c:url value='/Profil?user=${article.getUtilisateur().getNumeroUtilisateur()}'/>">
						${article.getUtilisateur().getPseudo()}</a>
					</td>
				</tr> 
				<c:if test="${from=='aquisition'}">
					<tr>
						<td class="col-4">
							<label>Téléphone : </label>
						</td>
						<td class="col-4">
							${ article.getUtilisateur().getTelephone() }
						</td>
					</tr>
				</c:if> 		
			</table>
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
					<input type="number" name="mise" min="${article.getPrixArticle()+1 }" max="${utilisateur.getCredit() }" value="${article.getPrixArticle()+1 }"> 
					<input type="submit" name="encherir" value="Encherir">
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
</body>
</html>