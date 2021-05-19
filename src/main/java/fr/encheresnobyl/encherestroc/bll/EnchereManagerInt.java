/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * @author mlebris2021
 * Interface which define the method available for EnchereManagerImpl
 */
public interface EnchereManagerInt {
	
	public void nouvelleEnchere(ArticleVendu article, Enchere enchere, Utilisateur utilisateur);

}
