<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container-fluid">
  <div class="row">
	  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
		  <a class="navbar-brand <c:if test="${empty utilisateur}">disabled</c:if>" href="<c:url value="/"/>">
        <img alt=""	src="<c:url value="assets/images/logo/logo-encheres-nobyl-2.png"/>"	width="300" height="100">
        <img src="${pageContext.request.contextPath}/assets/images/logo/radioactif.svg" alt="" width="70" height="70" class="mx-2">
	  	</a>
  		<!-- Toggler/collapsibe Button -->
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			  <span class="navbar-toggler-icon"></span>
		  </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		  <c:if test="${empty sessionId}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active btn btn-warning text-dark"
						href="<c:url value="/inscription"/>">Inscription</a></li>
					<li class="nav-item"><a class="nav-link active btn btn-secondary"
						href="<c:url value="/connexion"/>">Connexion</a></li>
				</ul>
	  	</c:if>
	  	<c:if test="${!empty utilisateur }">			
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active btn btn-warning text-dark coin" 
						href="<c:url value="/Mon-Profil"/>"> ${ utilisateur.getPseudo()} [${utilisateur.getCredit() } <%@ include file="credit.jsp" %>]
					</a></li>
					<li class="nav-item"><a class="nav-link active btn btn-secondary"
						href="<c:url value="/accueil"/>"> Enchère </a></li>
					<li class="nav-item"><a class="nav-link active btn btn-secondary"
						href="<c:url value="/VendreArticle"/>"> Vendre un article </a></li>
					<li class="nav-item"><a
						class="nav-link active btn btn-secondary"
						href="<c:url value="/deconnexion"/>"> Déconnexion </a></li>
				</ul>		
		  </c:if>
	  </div>
	</nav>
</div>