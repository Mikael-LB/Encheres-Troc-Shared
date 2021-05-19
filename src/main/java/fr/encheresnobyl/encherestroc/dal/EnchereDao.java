/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Interface en charge de définir les méthodes du DAO Enchere
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:24:59
 */
public interface EnchereDao {

	/**
	 * Méthode en charge de
	 * @param article 
	 * @param prixEnchere
	 * @param utilisateur
	 * @return 
	 */
	public void selectEnchere(Enchere enchere);

	/**
	 * Méthode en charge de
	 * @param article
	 * @return
	 */
	public List<Enchere> selectListeEncheres(ArticleVendu article);

}
