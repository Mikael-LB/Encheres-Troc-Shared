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
<title>Inscription</title>
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="col-6 col-sm-6 align-self-center">
					<form method="post" action="<c:url value="/connexion"/>">
						<div class="form-group row">
							<label for="login" class="col-sm-2 col-form-label">Identifiant
								:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="login" name="login"
									value="${ param.login }">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>