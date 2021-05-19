/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import fr.encheresnobyl.encherestroc.bo.Retrait;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * @author mlebris2021
 * Class which implements the method of RetraitManagerInt
 */
public class RetraitManagerImpl implements RetraitManagerInt {
	
	public Retrait selectByIdArticle(int id) {
		return DAOFactory.getRetraitDAO().selectByIdArticle(id);
	}
	
	public void insertNewRetrait(Retrait retrait, int idArticle) {
		DAOFactory.getRetraitDAO().insertNewRetrait(retrait, idArticle);
	}

}
