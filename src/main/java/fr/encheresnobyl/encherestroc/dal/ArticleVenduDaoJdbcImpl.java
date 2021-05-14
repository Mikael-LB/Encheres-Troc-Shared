/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:05
 */
public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao {

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<ArticleVendu> selectByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<ArticleVendu> selectByNomAndCategorie(String nom, int noCategorie) {
		// TODO Auto-generated method stub
		return null;
	}

}
