/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Categorie;
import fr.encheresnobyl.encherestroc.dal.CategorieDao;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * @author mlebris2021
 * class which implement the method of CategorieManager interface
 */
public class CategorieManagerImpl implements CategorieManagerInt {
	private CategorieDao categorieDAO = DAOFactory.getCategorieDAO();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categorie> getAllCategorie() {
		List<Categorie> lstCategorie = new ArrayList<>();
		try {
			lstCategorie= categorieDAO.getAllCategorie();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lstCategorie;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Categorie selectById(int id) {
		return categorieDAO.selectById(id);
	}

}
