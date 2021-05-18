/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Retrait;

/**
<<<<<<< HEAD
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 16:46:17
 */
public interface ArticleVenduManagerInt {

	
	public List<ArticleVendu> getEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres);
	
	public List<ArticleVendu> getEncheres(String string, int i);

	public List<ArticleVendu> getVentes(String motCle, int noCategorie, int noUtilisateur, List<String> Parametres);
	
	public ArticleVendu getArticleById(int id);
	
	public ArticleVendu insertNewArticle(ArticleVendu article, int noUtilisateur, Retrait retrait);
	
}
