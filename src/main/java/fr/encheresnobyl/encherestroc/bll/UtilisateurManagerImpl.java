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

	/**
	 * {@inheritDoc}
	 * @throws BusinessException 
	 */
	@Override
	public void checkInscriptionParam(String pseudo, String userName, String firstname, String email,
			String phone, String street, String postalCode, String city, String passwd, String passwdConfirm) throws BusinessException {
		BusinessException be = new BusinessException();

		checkForEmptyParam(pseudo, be);
		checkForEmptyParam(userName, be);
		checkForEmptyParam(firstname, be);
		checkForEmptyParam(email, be);
		checkForEmptyParam(phone, be);
		checkForEmptyParam(street, be);
		checkForEmptyParam(postalCode, be);
		checkForEmptyParam(city, be);
		checkForEmptyParam(passwd, be);
		checkForEmptyParam(passwdConfirm, be);

		checkPseudoNotExist(pseudo, be);
		checkValidEmail(email, be);
		checkPasswordAreEquals(passwd,passwdConfirm,be);
		
		if (be.hasError()) {
			throw be;
		}
	}

	/**
	 * Method to check if the passwords are equals
	 * @param passwd
	 * @param passwdConfirm
	 * @param be
	 */
	private void checkPasswordAreEquals(String passwd, String passwdConfirm, BusinessException be) {
		if (!passwdConfirm.equals(passwd)) {
			be.addError(CodesErreurBLL.PASSWORDS_DONT_MATCH);
		}		
	}

	/**
	 * Method to check if the email format is valid
	 * AND if it not exist
	 * @param email
	 * @param be
	 */
	private void checkValidEmail(String email, BusinessException be) {
		if (!email.matches("^[0-9a-zA-Z.]+@[0-9a-zA-Z]+.[a-zA-Z]{2,}$")) {
			be.addError(CodesErreurBLL.EMAIL_INVALID);
		}
		if (null != selectByMail(email)) {
			be.addError(CodesErreurBLL.EMAIL_ALREADY_EXIST);
		}
	}

	/**
	 * Method to check if a parameter is empty or null
	 * @param param
	 * @param be
	 */
	private void checkForEmptyParam(String param, BusinessException be) {
		if (param == null || param.trim().isEmpty()) {
			be.addError(CodesErreurBLL.EMPTY_PARAM);
		}
	}

	/**
	 * Method to check if pseudo param is valid
	 * AND if it not already exist
	 * @param pseudo
	 * @param be
	 */
	private void checkPseudoNotExist(String pseudo, BusinessException be) {
			if (!pseudo.matches("^[0-9a-zA-Z]+$")) {
				be.addError(CodesErreurBLL.PSEUDO_CHAR_NOT_ALLOWED);
			}
			if(null != selectByPseudo(pseudo)) {
				be.addError(CodesErreurBLL.PSEUDO_EXIST);
			}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur insertUtilisateur(Utilisateur user) {
		return DAOFactory.getUtilisateurDAO().insert(user);
	}
}
