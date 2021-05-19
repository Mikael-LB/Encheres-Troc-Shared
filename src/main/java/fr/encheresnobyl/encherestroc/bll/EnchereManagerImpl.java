/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

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
	public void nouvelleEnchere(ArticleVendu article, Enchere enchere, Utilisateur utilisateur) {
		
		if (enchere.getMontantEnchere()<=article.getPrixVente()) {
			//ERREUR PRIX INFERIEUR
		}
		
		if (enchere.getMontantEnchere()>utilisateur.getCredit()) {
			//ERREUR PAS ASSEZ DE CREDIT
		}
		
		enchereDao.nouvelleEnchere(article, enchere, utilisateur);
		
	}
	

}
