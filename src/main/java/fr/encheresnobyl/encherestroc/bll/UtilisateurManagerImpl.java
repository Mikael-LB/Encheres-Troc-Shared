/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 12:36:41
 */
public class UtilisateurManagerImpl implements UtilisateurManagerInt {
	
	/**
	* {@inheritDoc}
	*/
	public List<Utilisateur> selectAll(){	
		return DAOFactory.getUtilisateurDAO().selectAll();
	}
	
	/**
	* {@inheritDoc}
	*/
	public Utilisateur selectByPseudo(String pseudo) {
		return DAOFactory.getUtilisateurDAO().selectByPseudo(pseudo);
	}
	
	/**
	* {@inheritDoc}
	*/
	public Boolean selectByMail(String email) {
		return DAOFactory.getUtilisateurDAO().selectByMail(email);
	}
	
	/**
	* {@inheritDoc}
	*/
	public Utilisateur selectByIdentifiant(String identifiant) {
		return DAOFactory.getUtilisateurDAO().selectByIdentifiant(identifiant);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Utilisateur selectById(int id) {
		return DAOFactory.getUtilisateurDAO().selectById(id);
	}

}
