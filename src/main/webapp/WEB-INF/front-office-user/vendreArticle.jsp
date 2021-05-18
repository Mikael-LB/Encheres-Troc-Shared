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
<title>Vendre un article</title>
</head>
<body>
	<%@ include file="enTete.jsp" %>
	<div>
	  <img id="blah" src="#" alt="image de l'article" height="42" width="42"/>	
	</div>
	<div>
		<form action = "VendreArticle" method="POST" enctype="multipart/form-data">
		<label>
		Article :  
		</label>
		<input  type="text"  id="article" name="article"/>
		<br>
		<label>
		Description :  
		</label>
		<textarea id="description" name="description"></textarea>
		<br>
		<label>
		Catégorie :  
		</label>
		<select name="categorie" id="categorie">
			<c:forEach items="${ categories }" var="categorie" varStatus="status">
		    	<option value=${ categorie.noCategorie } >${categorie.libelle }</option>
			</c:forEach>
		</select>
		<br>
		<label>
		Photo de l'article : 
		</label>
		<input accept="image/*" type='file' id="photoArticle" name="photoArticle" multiple 
			onchange="readFilesAndDisplayPreview(this.files);" />
		<br>
		<label>
		Mise à prix :  
		</label>
		<input  type="number" id="miseAPrix" name="miseAPrix" />
		<label> points </label>
		<br>
		<label>
		Debut Enchère :  
		</label>
		<input  type="date" id="dateDebut" name="dateDebut" />
		<br>
		<label>
		Fin Enchère :  
		</label>
		<input  type="date" id="dateFin" name="dateFin" />
		<br>
		<div>
			Retrait
			<label>
			Rue :  
			</label>
			<input  type="text"  id="retraitRue" name="retraitRue" value="${utilisateur.getRue()}" />
			<br>
			<label>
			Code Postal :  
			</label>
			<input  type="text" id="retraitCP" name="retraitCP" value="${utilisateur.getCodePostal() }"/>
			<br>
			<label>
			Ville :  
			</label>
			<input  type="text" id="retraitVille" name="retraitVille" value="${utilisateur.getVille() }" />
			<br>
		</div>
		<input type="submit" name="submit" value="envoyer">
		<input type="reset" name="reset" value="annuler">
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