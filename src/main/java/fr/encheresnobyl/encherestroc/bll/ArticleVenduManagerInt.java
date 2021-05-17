/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
<<<<<<< HEAD
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 16:46:17
 */
public interface ArticleVenduManagerInt {


	/**
	 * Méthode en charge de
	 * @param nom
	 * @param noCategorie
	 * @return
	 */
	List<ArticleVendu> getEncheresEnCours(String nom, int noCategorie);

	/**
	 * Méthode en charge de
	 * @param nom
	 * @param noCategorie
	 * @param noUtilisateur
	 * @return
	 */
	List<ArticleVendu> getEncheresUtilisateur(String nom, int noCategorie, int noUtilisateur);

	/**
	 * Méthode en charge de
	 * @param nom
	 * @param noCategorie
	 * @param noUtilisateur
	 * @return
	 */
	List<ArticleVendu> getEncheresRemporteesUtilisateur(String nom, int noCategorie, int noUtilisateur); 
	
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres);
	
}
