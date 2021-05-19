<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	  			
			<div class="navbar-brand"> ENI Enchères</div>
			  <!-- Toggler/collapsibe Button -->
  				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    			<span class="navbar-toggler-icon"></span>
  				</button>
			
				<c:if test="${empty sessionId}">
					<div class="collapse navbar-collapse" id="collapsibleNavbar">
				 	<ul class="navbar-nav">
      					<li class="nav-item">
							<a class="nav-link active" href="<c:url value="/inscription"/>">Inscription</a>
						</li>
						<li class="nav-item">
							<a class="nav-link active" href="<c:url value="/connexion"/>">Connexion</a>
						</li>
					</ul>
					</div>
				</c:if>
				<c:if test="${!empty utilisateur }">
				<div class="collapse navbar-collapse" id="collapsibleNavbar" >
					<ul class="navbar-nav">
      					<li class="nav-item">
							<a class="nav-link active " href="<c:url value="/Mon-Profil"/>"> ${ utilisateur.getPseudo() } </a> 
						</li>
						<li class="nav-item">
							<a class="nav-link active" href="<c:url value="/accueil"/>"> Enchère  </a> 
						</li>
						<li class="nav-item">
							<a class="nav-link active " href="<c:url value="/VendreArticle"/>"> Vendre un article  </a> 
						</li>
						<li class="nav-item">
							<a class="nav-link active " href="<c:url value="/deconnexion"/>"> Déconnexion  </a> 
						</li>
					</ul>
				</div>
				</c:if>
		
	</nav>
</div>