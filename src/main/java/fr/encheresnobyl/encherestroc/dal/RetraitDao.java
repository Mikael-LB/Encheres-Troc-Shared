/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import fr.encheresnobyl.encherestroc.bo.Retrait;

/**
 * Interface en charge de définir les méthodes du DAO Retrait
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:25:48
 */
public interface RetraitDao {

	/**
	 * Méthode en charge de
	 * @param id
	 * @return
	 */
	public Retrait selectByIdArticle(int id);
	
	public void insertNewRetrait(Retrait retrait, int idArticle);

}
