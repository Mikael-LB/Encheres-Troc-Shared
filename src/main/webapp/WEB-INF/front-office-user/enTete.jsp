<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<nav class="navbar navbar-dark bg-dark">
	  	<div class="container-fluid">		
			<div class="navbar-brand">ENI Enchères</div>
			<div class="d-flex" id="navbarText">
				<c:if test="${empty sessionId}">
					<a class="nav-link active" href="${request.getContextPath() }/inscription">Inscription</a>
					<a class="nav-link active" href="${request.getContextPath() }/connexion">Connexion</a>
				</c:if>
				<c:if test="${!empty sessionId }">
					<a class="nav-link active" href="${request.getContextPath() }//Mon-Profil"> ${ sessionPseudo } </a> 
					<a class="nav-link active" href="${request.getContextPath() }/accueil"> Enchère  </a> 
					<a class="nav-link active" href="${request.getContextPath() }/vendre"> Vendre un article  </a> 
					<a class="nav-link active" href="${request.getContextPath() }/deconnexion"> Déconnexion  </a> 
				</c:if>
			</div>
		</div>
	</nav>
</div>