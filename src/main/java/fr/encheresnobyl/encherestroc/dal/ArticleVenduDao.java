/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
 * Interface en charge de définir les méthodes du DAO ArticleVendu
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:23:59
 */
public interface ArticleVenduDao {

	/**
	 * Méthode en charge de récupérer une liste d'articles en fonction du nom des articles
	 * @param nom
	 * @return
	 */
	List<ArticleVendu> selectByNom(String nom);
	

	/**
	 * Méthode en charge de récupérer une liste d'articles en fonction du nom des articles et de leur catégorie
	 * @param nom
	 * @param noCategorie
	 * @return
	 */
	List<ArticleVendu> selectByNomAndCategorie(String nom, int noCategorie);
	
}
