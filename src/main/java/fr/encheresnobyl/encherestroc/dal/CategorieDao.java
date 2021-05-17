/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.SQLException;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Categorie;

/**
 * @author mlebris2021
 * Interface which list the DAO method for accessing Categorie table
 */
public interface CategorieDao {
	
	/**
	 * Method to get all Categorie from the db
	 * @return a List of Categorie
	 * @throws SQLException 
	 */
	List<Categorie> getAllCategorie() throws SQLException;

	/**
	 * Méthode en charge de
	 * @param id
	 * @return 
	 */
	Categorie selectById(int id);

}
