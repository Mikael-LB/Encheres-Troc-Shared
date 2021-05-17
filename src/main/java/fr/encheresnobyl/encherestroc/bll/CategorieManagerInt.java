/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Categorie;

/**
 * @author mlebris2021
 * Interface which define the method for the Manager of Categorie
 */
public interface CategorieManagerInt {

	/**
	 * Method to ask the DAO to get all the Categorie from the db
	 * @return
	 */
	List<Categorie> getAllCategorie();
	Categorie selectCategorieById(int no_categorie);
}
