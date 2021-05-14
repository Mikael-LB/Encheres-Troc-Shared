/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.dal.ArticleVenduDao;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * @author mlebris2021 Class which implements the method of
 *         ArticleVenduManagerInt
 */
public class ArticleVenduManagerImpl implements ArticleVenduManagerInt {
	private ArticleVenduDao articleVenduDAO = DAOFactory.getArticleVenduDAO();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getByNom(String nom) {
		List<ArticleVendu> lstArticleVendu = new ArrayList<>();
		try {
			lstArticleVendu = articleVenduDAO.selectByNom(nom);
		} catch (Exception e) {
			e.printStackTrace();
			//TODO throw BusinessException
		}
		return lstArticleVendu;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getByNomAndCategorie(String nom, String noCategorie) {
		List<ArticleVendu> lstArticleVendu = new ArrayList<>();
		try {
			int noCategorieInt = Integer.parseInt(noCategorie);
			lstArticleVendu = articleVenduDAO.selectByNomAndCategorie(nom, noCategorieInt);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			//TODO throw BusinessException
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstArticleVendu;
	}

}
