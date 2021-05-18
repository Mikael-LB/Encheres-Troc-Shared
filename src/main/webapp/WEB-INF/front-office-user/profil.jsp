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
<title>Profil</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<div align= "center">
		<c:if test="${empty modifier }" ><h1>Profil</h1></c:if>
		<c:if test="${!empty modifier }" ><h1>Mon Profil</h1></c:if>
	</div>
	<div align="center">
	<p>${user.toString() }</p>
		<table>
			<tr>
				<td>Pseudo : </td>
				<td>${ user.getPseudo() }</td>
			</tr>
			<tr>
				<td>Nom : </td>
				<td>${ user.getNom() }</td>
			</tr>
			<tr>
				<td>Prenom : </td>
				<td>${ user.getPrenom() }</td>
			</tr>
			<tr>
				<td>Email : </td>
				<td>${ user.getEmail() }</td>
			</tr>
			<tr>
				<td>Telephone : </td>
				<td>${ user.getTelephone() }</td>
			</tr>
			<tr>
				<td>Rue : </td>
				<td>${ user.getRue() }</td>
			</tr>
			<tr>
				<td>Code Postal : </td>
				<td>${ user.getCodePostal() }</td>
			</tr>
			<tr>
				<td>Ville : </td>
				<td>${ user.getVille() }</td>
			</tr>				
		</table>
	</div>
	<div align=center >
		<c:if test="${!empty modifier }" >
			<form action="Modifier-Profil">
			<input type="hidden" name="numeroUtilisateur" value="${utilisateur.getNumeroUtilisateur() }">
			<input type="submit" name="modifier" value="Modifier">
			</form>
		</c:if>
	</div>
	
</body>
</html>