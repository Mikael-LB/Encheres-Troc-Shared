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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getEncheresEnCours(String nom, int noCategorie) {
		
		if ((nom==null || nom=="") && noCategorie==0) {
			return articleVenduDao.selectAllDispo();			
		}else if (nom==null || nom=="") {
			return articleVenduDao.selectDispoByNom(nom);
		}else {
			return articleVenduDao.selectDispoByNomAndCategorie(nom, noCategorie);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getEncheresUtilisateur(String nom, int noCategorie, int noUtilisateur) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> getEncheresRemporteesUtilisateur(String nom, int noCategorie, int noUtilisateur) {
		return null;
	}
	
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres){
		return articleVenduDao.selectEncheres(motCle, noCategorie, noUtilisateur, Parametres);
		
	}



}
