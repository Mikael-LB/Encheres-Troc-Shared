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
	 * Méthode en charge de récupérer la liste de l'ensemble des articles disponibles aux enchères.
	 * @return
	 */
	List<ArticleVendu> selectAllDispo();

	/**
	 * Méthode en charge de récupérer une liste d'articles disponibles aux enchères en fonction du nom des articles
	 * @param nom
	 * @return
	 */
	List<ArticleVendu> selectDispoByNom(String nom);
	

	/**
	 * Méthode en charge de récupérer une liste d'articles disponibles aux enchères en fonction du nom des articles et de leur catégorie
	 * @param nom
	 * @param noCategorie
	 * @return
	 */
	List<ArticleVendu> selectDispoByNomAndCategorie(String nom, int noCategorie);

	/**
	 * Méthode en charge de
	 * @param motCle
	 * @param noCategorie
	 * @param noUtilisateur
	 * @param parametres
	 * @return
	 */
	List<ArticleVendu> selectEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> parametres);
	
}
