/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import fr.encheresnobyl.encherestroc.bo.Retrait;

/**
 * @author mlebris2021
 * Interface which define the method available for RetraitManagerImpl
 */
public interface RetraitManagerInt {
	
	Retrait selectByIdArticle(int id);

	public void insertNewRetrait(Retrait retrait, int idArticle);
}
