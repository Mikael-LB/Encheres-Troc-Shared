/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Retrait;
import fr.encheresnobyl.encherestroc.dal.ArticleVenduDao;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * Classe en charge
 * 
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 16:46:29
 */
public class ArticleVenduManagerImpl implements ArticleVenduManagerInt {

	private ArticleVenduDao articleVenduDao = DAOFactory.getArticleVenduDAO();

	public List<ArticleVendu> getEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres) {
		return articleVenduDao.selectEncheres(motCle, noCategorie, noUtilisateur, Parametres);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie) {
		return articleVenduDao.selectEncheres(motCle, noCategorie);
	}

	public List<ArticleVendu> getVentes(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres) {
		return articleVenduDao.selectVentes(motCle, noCategorie, noUtilisateur, Parametres);

	}

	public ArticleVendu getArticleById(int id) {
		return articleVenduDao.selectArticleById(id);
	}

	public ArticleVendu insertNewArticle(ArticleVendu article, int noUtilisateur, Retrait retrait) throws BusinessException {

		BusinessException be = new BusinessException();

		if (article.getNomArticle() == null || "".equals(article.getNomArticle())) {
			be.addError(CodesErreurBLL.NOM_ARTICLE_EMPTY);
		}
		if (article.getDescription() == null || "".equals(article.getDescription())) {
			be.addError(CodesErreurBLL.DESCRIPTION_ARTICLE_EMPTY);
		}
		if (article.getDateDebutEncheres() == null || article.getDateFinEncheres() == null) {
			be.addError(CodesErreurBLL.DATES_ARTICLE_EMPTY);
		}
		if (article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())
				|| article.getDateDebutEncheres().isEqual(article.getDateFinEncheres())) {
			be.addError(CodesErreurBLL.DATES_ARTICLE_INCORRECTES);
		}
		if (article.getPrixArticle() <= 0) {
			be.addError(CodesErreurBLL.PRIX_ARTICLE_INVALIDE);
		}

		if (retrait.getRue() == null || "".equals(retrait.getRue())) {
			be.addError(CodesErreurBLL.RUE_RETRAIT_EMPTY);
		}
		if (retrait.getCodePostal() == null || "".equals(retrait.getCodePostal())) {
			be.addError(CodesErreurBLL.CPO_RETRAIT_EMPTY);
		}
		if (retrait.getVille() == null || "".equals(retrait.getVille())) {
			be.addError(CodesErreurBLL.VILLE_RETRAIT_EMPTY);
		}

		if (be.hasError()) {
			throw be;
		}

		return articleVenduDao.insertNewArticle(article, noUtilisateur, retrait);
	}

}
