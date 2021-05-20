<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container-fluid">
  <div class="row">
	  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
		  <a class="navbar-brand" href="<c:url value="/"/>">
        <img alt=""	src="<c:url value="assets/images/logo/logo-eni-encheres.png"/>"	width="300" height="100">
        ENI Enchères
        <img src="${pageContext.request.contextPath}/assets/images/logo/radioactif.svg" alt="" width="70" height="70" class="mx-2">
				<span>ENCHERES NOBYL</span>
	  	</a>
  		<!-- Toggler/collapsibe Button -->
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			  <span class="navbar-toggler-icon"></span>
		  </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		  <c:if test="${empty sessionId}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/inscription"/>">Inscription</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/connexion"/>">Connexion</a></li>
				</ul>
	  	</c:if>
	  	<c:if test="${!empty utilisateur }">			
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active" 
						href="<c:url value="/Mon-Profil"/>"> ${ utilisateur.getPseudo() }
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/accueil"/>"> Enchère </a></li>
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/VendreArticle"/>"> Vendre un article </a></li>
					<li class="nav-item"><a
						class="nav-link active btn btn-outline-secondary"
						href="<c:url value="/deconnexion"/>"> Déconnexion </a></li>
				</ul>		
		  </c:if>
	  </div>
	</nav>
</div>