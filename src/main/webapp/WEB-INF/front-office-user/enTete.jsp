<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	  			
			<a class="navbar-brand" style="font-weight: bold" href="<c:url value="/"/>">			
				<img src="${pageContext.request.contextPath}/assets/images/logo/radioactif.svg" alt="" width="70" height="70" class="mx-2">
				<span>ENCHERES NOBYL</span>
			</a>
			  <!-- Toggler/collapsibe Button -->
  				<button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    			<span class="navbar-toggler-icon"></span>
  				</button>
			
				<div class="collapse navbar-collapse mx-4" id="collapsibleNavbar">
				<c:if test="${empty sessionId}">
				 	<ul class="navbar-nav ms-auto">
      					<li class="nav-item">
							<a class="nav-link active" href="<c:url value="/inscription"/>">Inscription</a>
						</li>
						<li class="nav-item">
							<a class="nav-link active" href="<c:url value="/connexion"/>">Connexion</a>
						</li>
					</ul>
				</c:if>
				
				<c:if test="${!empty utilisateur }">
				
					<ul class="navbar-nav ms-auto">
      					<li class="nav-item ">
							<a class="nav-link active " href="<c:url value="/Mon-Profil"/>"> ${ utilisateur.getPseudo() } [${utilisateur.getCredit() } <%@ include file="credit.jsp" %>] </a> 
						</li>
						<li class="nav-item ">
							<a class="nav-link active" href="<c:url value="/AccueilServlet"/>"> Enchère  </a> 
						</li>
						<li class="nav-item ">
							<a class="nav-link active " href="<c:url value="/VendreArticle"/>"> Vendre un article  </a> 
						</li>
						<li class="nav-item ">
							<a class="nav-link active " href="<c:url value="/deconnexion"/>"> Déconnexion  </a> 
						</li>
					</ul>
				</c:if>
				</div>
	</nav>
</div>