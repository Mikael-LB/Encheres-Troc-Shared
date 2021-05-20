/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;
import fr.encheresnobyl.encherestroc.dal.UtilisateurDao;
import fr.encheresnobyl.encherestroc.dal.UtilisateurDaoJdbcImpl;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 12:36:41
 */
public class UtilisateurManagerImpl implements UtilisateurManagerInt {

	private static final int PSEUDO_DB_LENGTH = 30;
	private static final int USERNAME_DB_LENGTH = 30;
	private static final int FIRSTNAME_DB_LENGTH = 30;
	private static final int EMAIL_DB_LENGTH = 50;
	private static final int PHONE_DB_LENGTH = 15;
	private static final int STREET_DB_LENGTH = 30;
	private static final int POSTAL_CODE_DB_LENGTH = 10;
	private static final int CITY_DB_LENGTH = 50;
	private static final int PASSWD_DB_LENGTH = 30;

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
		checkTooLongParam(pseudo, PSEUDO_DB_LENGTH, be);
		checkForEmptyParam(userName, be);
		checkTooLongParam(userName, USERNAME_DB_LENGTH, be);
		checkForEmptyParam(firstname, be);
		checkTooLongParam(firstname, FIRSTNAME_DB_LENGTH, be);
		checkForEmptyParam(email, be);
		checkTooLongParam(email, EMAIL_DB_LENGTH, be);
		checkForEmptyParam(phone, be);
		checkTooLongParam(phone, PHONE_DB_LENGTH, be);
		checkForEmptyParam(street, be);
		checkTooLongParam(street, STREET_DB_LENGTH, be);
		checkForEmptyParam(postalCode, be);
		checkTooLongParam(postalCode, POSTAL_CODE_DB_LENGTH, be);
		checkForEmptyParam(city, be);
		checkTooLongParam(city, CITY_DB_LENGTH, be);
		checkForEmptyParam(passwd, be);
		checkTooLongParam(passwd, PASSWD_DB_LENGTH, be);
		checkForEmptyParam(passwdConfirm, be);

		checkPseudoNotExist(pseudo, be);
		checkValidEmail(email, be);
		checkValidPhone(phone,be);
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
		if (passwd.length() < 8) {
			be.addError(CodesErreurBLL.PASSWORD_TO_SHORT);
		}
		if (!passwdConfirm.equals(passwd)) {
			be.addError(CodesErreurBLL.PASSWORDS_DONT_MATCH);
		}		
	}
	/**
	 * Method to check if phone number contains only number
	 * @param phone
	 * @param be
	 */
	private void checkValidPhone(String phone, BusinessException be) {
		if (!phone.matches("^[0-9]{1,15}$")) {
			be.addError(CodesErreurBLL.PHONE_ONLY_NUMBER);
		}		
	}


	/**
	 * Method to check if the email format is valid
	 * AND if it not exist
	 * @param email
	 * @param be
	 */
	private void checkValidEmail(String email, BusinessException be) {
		if (!email.matches("^[0-9a-zA-Z.-]+@[0-9a-zA-Z-]{2,}.[a-zA-Z]{2,}$")
				|| email.length() > 50) {
			be.addError(CodesErreurBLL.EMAIL_INVALID);
		}
		if (true==selectByMail(email)) {
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
			List<Integer>lstCodes = be.getLstErrorCodes();
			if (!lstCodes.contains(CodesErreurBLL.EMPTY_PARAM)) {
				be.addError(CodesErreurBLL.EMPTY_PARAM);
			}
		}
	}
	
	/**
	 * Method to check if param length is under the max allowed
	 * @param param
	 * @param maxLength
	 * @param be
	 */
	private void checkTooLongParam(String param, int maxLength, BusinessException be) {
		if (param.length() > maxLength) {
			be.addError(CodesErreurBLL.PARAM_TOO_LONG);
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
	 * @throws BusinessException 
	 */
	@Override
	public Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException {
		return DAOFactory.getUtilisateurDAO().insert(user);
	}

	@Override
	public void modifieProfil(Utilisateur utilisateur, Utilisateur user, String passwdVerif) throws BusinessException {
		BusinessException be = new BusinessException();
		if(utilisateur.getMotDePasse().equals(passwdVerif)) {
			user.setNumeroUtilisateur(utilisateur.getNumeroUtilisateur());
			UtilisateurDao utilisateurDao =new UtilisateurDaoJdbcImpl();
			utilisateurDao.updateProfil(user);
			
		}else {
			be.addError(CodesErreurBLL.PASSWORD_FALSE);
		}
		
	}

	@Override
	public void checkUpdateParam(Utilisateur utilisateur, String pseudo, String userName, String firstname, String email,
			String phone, String street, String postalCode, String city, String passwd, String passwdConfirm)
			throws BusinessException {
		BusinessException be = new BusinessException();
		checkForEmptyParam(pseudo, be);
		if(!utilisateur.getPseudo().equals(pseudo)) {
			checkTooLongParam(pseudo, PSEUDO_DB_LENGTH, be);
			checkPseudoNotExist(pseudo, be);
			System.out.println("la!");
		}
		checkForEmptyParam(userName, be);
		checkTooLongParam(userName, USERNAME_DB_LENGTH, be);
		checkForEmptyParam(firstname, be);
		checkTooLongParam(firstname, FIRSTNAME_DB_LENGTH, be);
		checkForEmptyParam(email, be);
		if(!utilisateur.getEmail().equals(email)) {
			checkTooLongParam(email, EMAIL_DB_LENGTH, be);
			checkValidEmail(email, be);
			System.out.println("lala!");
		}
		checkForEmptyParam(phone, be);
		checkTooLongParam(phone, PHONE_DB_LENGTH, be);
		checkForEmptyParam(street, be);
		checkTooLongParam(street, STREET_DB_LENGTH, be);
		checkForEmptyParam(postalCode, be);
		checkTooLongParam(postalCode, POSTAL_CODE_DB_LENGTH, be);
		checkForEmptyParam(city, be);
		checkTooLongParam(city, CITY_DB_LENGTH, be);
		checkForEmptyParam(passwd, be);
		checkTooLongParam(passwd, PASSWD_DB_LENGTH, be);
		checkForEmptyParam(passwdConfirm, be);

		
		
		checkValidPhone(phone,be);
		checkPasswordAreEquals(passwd,passwdConfirm,be);

		if (be.hasError()) {
			throw be;
		}
		
	}
}
