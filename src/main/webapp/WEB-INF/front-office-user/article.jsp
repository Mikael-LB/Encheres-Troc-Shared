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
	<br>
	<h1 align="center">${ message }</h1>
	<br>
	<br>
	<div class="row">
		<div class="col-4">
			<img src="https://via.placeholder.com/150">
		</div>
		<div class="col-8">
			<h2 align="center">${ article.getNomArticle() }</h2>
			<br>
			<table>
				<tr>
					<td>
						<label>Description : </label>
					</td>
					<td>
						${ article.getDescription() }
					</td>
				</tr>
				<c:if test="${from=='aquisition'||from=='maVente'||from=='enCour'}">
					<tr>
						<td>
							<label>Meilleur offre : </label>
						</td>
						<td>
							${ article.getPrixVente() }
						</td>
					</tr>
				</c:if> 
				<tr>
					<td>
						<label>Mise à prix : </label>
					</td>
					<td>
						${ article.getMiseAPrix() }
					</td>
				</tr>
				<c:if test="${from=='miseEnVente'|| from=='maVente'|| from=='enCour'}">
					<tr>
						<td>
							<label>Date de début d'enchère : </label>
						</td>
						<td>
							${article.getDateDebutEncheres()}
						</td>
					</tr>
					<tr>
						<td>
							<label>Date de fin d'enchère : </label>
						</td>
						<td>
							${article.getDateFinEncheres()}							
						</td>
					</tr>
				</c:if>  
				<tr>
					<td>
						<label>Retrait : </label>
					</td>
					<td>
						${ article.getPointRetrait().getRue() }<br>
						${ article.getPointRetrait().getCodePostal() } ${ article.getPointRetrait().getVille() }
					</td>
				</tr>
				<tr>
					<td>
						<label>Vendeur : </label>
					</td>
					<td>
						<a href="<c:url value='/Profil?user=${article.getUtilisateur().getNumeroUtilisateur()}'/>">
						${article.getUtilisateur().getPseudo()}</a>
					</td>
				</tr> 
				<c:if test="${from=='aquisition'}">
					<tr>
						<td>
							<label>Téléphone : </label>
						</td>
						<td>
							${ article.getUtilisateur().getTelephone() }
						</td>
					</tr>
				</c:if> 		
			</table>
			<br>
			<c:if test="${from=='miseEnVente'}" >
				<a href="<c:url value="/" />" > <input type="button" value="accueil"> </a>
			</c:if>
			<c:if test="${from=='aquisition'}">
				<a href="#" > <input type="button" value="retour"> </a>
			</c:if>
			<c:if test="${from=='maVente'}">
				<a href="#" > <input type="button" value="Retrait effectué"> </a>
			</c:if>
		</div>
	</div>
</body>
</html>