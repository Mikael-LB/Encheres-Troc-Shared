
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1.0" />
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Vendre un article</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<div class="container d-flex justify-content-center">
	
	<div class="justify-content-center col-6">
		<h1 class="text-center my-4">Vendre un article</h1>

		<div>
			<form action = "VendreArticle" method="POST" enctype="multipart/form-data">
				<div class="form-group mb-3">
				<label>Article :</label>
				<input class="form-control" type="text"  id="article" name="article"/>
				</div>
				
				<div class="form-group mb-3">
				<label> Description :</label>
				<textarea class="form-control" id="description" name="description"></textarea>
				</div>
				
				<div class="form-group mb-3">
				<label>
				Catégorie :  
				</label>
				<select class="form-control" name="categorie" id="categorie">
					<c:forEach items="${ categories }" var="categorie" varStatus="status">
				    	<option value=${ categorie.noCategorie } >${categorie.libelle }</option>
					</c:forEach>
				</select>
				</div>
				
				<div class="form-group mb-3">
				<label>
				Photo de l'article : 
				</label>
				<input class="form-control mb-3" accept="image/*" type='file' id="photoArticle" name="photoArticle" multiple 
					onchange="readFilesAndDisplayPreview(this.files);" />
				</div>
				
				<div class="form-group mb-3">
				<label>
				Mise à prix :  
				</label>
				<input class="form-control" type="number" id="miseAPrix" name="miseAPrix" />
				</div>
				
				<div class="form-group mb-3">
				<label>
				Debut Enchère :  
				</label>
				<input class="form-control" type="date" id="dateDebut" name="dateDebut" />
				</div>
				
				<div class="form-group mb-3">
				<label>
				Fin Enchère :  
				</label>
				<input class="form-control" type="date" id="dateFin" name="dateFin" />
				</div>
				
				<div class="form-group mb-3">
				Retrait
				<label>
				Rue :  
				</label>
				<input class="form-control" type="text"  id="retraitRue" name="retraitRue" value="${utilisateur.getRue()}" />
				</div>
			
				<div class="form-group mb-3">
				<label>
				Code Postal :  
				</label>
				<input class="form-control" type="text" id="retraitCP" name="retraitCP" value="${utilisateur.getCodePostal() }"/>
				</div>
			
				<div class="form-group mb-3">
				<label>
				Ville :  
				</label>
				<input class="form-control" type="text" id="retraitVille" name="retraitVille" value="${utilisateur.getVille() }" />
				</div>

				<input class="btn btn-primary" type="submit" name="submit" value="envoyer">
				<input class="btn btn-primary" type="reset" name="reset" value="annuler">
			</form>
		
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
		</div>
</div>
</body>
<script type="text/javascript">
	photoArticle.onchange = evt => {
		  const [file] = photoArticle.files
		  if (file) {
		    blah.src = URL.createObjectURL(file)
		  }
		}
</script>
</html>