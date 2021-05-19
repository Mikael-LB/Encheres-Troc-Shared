/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;
import fr.encheresnobyl.encherestroc.dal.EnchereDao;

/**
 * @author mlebris2021
 * Class which implements the method of EnchereManagerInt
 */
public class EnchereManagerImpl implements EnchereManagerInt {

	EnchereDao enchereDao = DAOFactory.getEnchereDAO();
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public ArticleVendu nouvelleEnchere(Enchere enchere) {
		
		if (enchere.getMontantEnchere()<=enchere.getArticleVendu().getPrixVente()) {
			//ERREUR PRIX INFERIEUR
		}
		
		if (enchere.getMontantEnchere()>enchere.getUtilisateur().getCredit()) {
			//ERREUR PAS ASSEZ DE CREDIT
		}
		
		enchereDao.selectEnchere(enchere);
		
		enchere.getArticleVendu().setListeEncheres(getListeEncheres(enchere.getArticleVendu()));
		return enchere.getArticleVendu();
		
	}
	
	public List<Enchere> getListeEncheres(ArticleVendu article) {
		
		return enchereDao.selectListeEncheres(article);
	}
	

}
