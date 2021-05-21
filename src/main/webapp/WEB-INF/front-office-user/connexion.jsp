<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Connexion</title>
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Connection</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="col-6 col-sm-6 align-self-center">
					<form method="post" action="<c:url value="/connexion"/>">
						<div class="form-group row">
							<label for="login" class="col-sm-3 col-form-label">Identifiant
								:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="login" name="login"
									value="${ param.login }">
							</div>
						</div>
						<div class="form-group row">
							<label for="password" class="col-sm-3 col-form-label">Mot
								de passe :</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="password"
									name="password">
							</div>
						</div>
						<c:if test="${!empty errorList}">
							<div class="form-group row">
								<div class="col-sm-12 col-form-label">
									<c:forEach var="error" items="${errorList }">
										<p class="text-danger">${messageReader.getMessageErreur(error)}</p>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<div class="form-group row">
							<div class="col-sm-3">
								<button type="submit" class="btn btn-warning">Connexion</button>
							</div>
							<div class="col-sm-7">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="remember"
										name="remember"> <label class="form-check-label"
										for="remember">Se souvenir de moi</label>
								</div>
								<div>
									<!-- TODO -->
									<a href="">Mot de passe oublié</a>
								</div>
							</div>
						</div>
					</form>
					<a class="btn btn-secondary"" href="<c:url value="/inscription"/>">Créer
						un compte</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
	
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>