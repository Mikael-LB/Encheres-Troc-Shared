/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.dal.ArticleVenduDao;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 16:46:29
 */
public class ArticleVenduManagerImpl implements ArticleVenduManagerInt{
	
	private ArticleVenduDao articleVenduDao = DAOFactory.getArticleVenduDAO();
	
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres){
		return articleVenduDao.selectEncheres(motCle, noCategorie, noUtilisateur, Parametres);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie) {
		return articleVenduDao.selectEncheres(motCle, noCategorie);
	}
	
	public List<ArticleVendu> getVentes(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres){
		return articleVenduDao.selectVentes(motCle, noCategorie, noUtilisateur, Parametres);
		
	}
	

	public ArticleVendu getArticleById(int id) {
		return articleVenduDao.selectArticleById(id);
	}



}
