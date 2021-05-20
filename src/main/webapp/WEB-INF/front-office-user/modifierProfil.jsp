<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Modification du profil</title>
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Mon profil</h1>
			</div>
		</div>
		<form class="row g-3" method="post"
			action="<c:url value="/ModifierProfil"/>">
			<div class="col-6">
				<label for="pseudo" class="col-sm-2 col-form-label">Pseudo</label> <input
					type="text" class="form-control" id="pseudo" name="pseudo"
					value="${ utilisateur.getPseudo() }">
			</div>
			<div class="col-6">
				<label for="userName" class="col-sm-2 col-form-label">Nom</label> <input
					type="text" class="form-control" id="userName" name="userName"
					value="${ utilisateur.getNom() }">
			</div>
			<div class="col-6">
				<label for="firstname" class="col-sm-2 col-form-label">Prénom</label>
				<input type="text" class="form-control" id="firstname"
					name="firstname" value="${ utilisateur.getPrenom() }">
			</div>
			<div class="col-6">
				<label for="email" class="col-sm-2 col-form-label">Email</label> <input
					type="email" class="form-control" id="email" name="email"
					value="${ utilisateur.getEmail() }">
			</div>
			<div class="col-6">
				<label for="phone" class="col-sm-2 col-form-label">Téléphone</label>
				<input type="tel" class="form-control" id="phone" name="phone"
					value="${ utilisateur.getTelephone() }">
			</div>
			<div class="col-6">
				<label for="street" class="col-sm-2 col-form-label">Rue</label> <input
					type="text" class="form-control" id="street" name="street"
					value="${ utilisateur.getRue() }">
			</div>
			<div class="col-6">
				<label for="postalCode" class="col-sm-2 col-form-label">Code
					postal</label> <input type="text" class="form-control" id="postalCode"
					name="postalCode" value="${ utilisateur.getCodePostal() }">
			</div>
			<div class="col-6">
				<label for="city" class="col-sm-2 col-form-label">Ville</label> <input
					type="text" class="form-control" id="city" name="city"
					value="${ utilisateur.getVille() }">
			</div>
			<div class="col-6">
				<label for="passwd" class="col-form-label">Nouveau Mot de
					passe</label> <input type="password" class="form-control" id="passwd"
					name="passwd" value="" placeholder="laissez vide pour conserver le mot de passe">
			</div>
			<div class="col-6">
				<label for="passwdConfirm" class="col-form-label">Confirmez votre nouveau mot de passe</label> <input type="password" class="form-control" id="passwdConfirm"
					name="passwdConfirm">
			</div>
			<div class="col-6">
				<label for="passwd" class="col-form-label">Verification de l'ancien mot de passe</label>
				<input type="password" class="form-control" id="passwdVerif"
					name="passwdVerif" value="">
			</div>
		
		<div class="col-12">
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
			

				<div class="col-6">
					<button type="submit" class="btn btn-primary">Modifier</button>
				</div>
				<div class="col-6">
					<a class="btn btn-primary" href="<c:url value="/"/>">Annuler</a>
				</div>
			
		</form>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>