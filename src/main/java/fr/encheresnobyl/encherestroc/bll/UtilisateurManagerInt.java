/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 15:12:43
 */
public interface UtilisateurManagerInt {
	
	/**
	 * Méthode en charge de retourner une liste de tous les utilisateurs.
	 * @return
	 */
	public List<Utilisateur> selectAll();
	
	/**
	 * Méthode en charge de retourner un utilisateur à partir de son pseudo
	 * @param pseudo
	 * @return
	 */
	public Utilisateur selectByPseudo(String pseudo);
	
	/**
	 * Méthode en charge de vérifier si un utilisateur est déjà enregistré à partir d'un mail
	 * @param email
	 * @return
	 */
	public Boolean selectByMail(String email);
	
	/**
	 * Méthode en charge de retoutner un utilisateur à partir de son identifiant (pseudo ou mail).
	 * @param identifiant
	 * @return
	 */
	public Utilisateur selectByIdentifiant(String identifiant);
	
	public Utilisateur selectById(int id);

	/**
	 * Method to check the parameters for a Utilisateur
	 * @param pseudo
	 * @param userName
	 * @param firstname
	 * @param email
	 * @param phone
	 * @param street
	 * @param postalCode
	 * @param city
	 * @param passwd
	 * @param passwdConfirm
	 * @throws BusinessException 
	 */
	public void checkInscriptionParam(String pseudo, String userName, String firstname, String email, String phone,
			String street, String postalCode, String city, String passwd, String passwdConfirm) throws BusinessException;

	/**
	 * Method to ask DAO to insert a Utilisateur
	 * @param user
	 * @return an Utilisateur with his id
	 * @throws BusinessException 
	 */
	public Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException;

	public void modifieProfil(Utilisateur utilisateur, Utilisateur user, String passwdVerif) throws BusinessException ;

	public void checkUpdateParam(Utilisateur attribute, String pseudo, String userName, String firstname, String email,
			String phone, String street, String postalCode, String city, String passwd, String passwdConfirm)throws BusinessException;

  	/**
	 * Method to ask the DAO to delete a Utilisateur
	 * as his request
	 * @param user
	 * @throws BusinessException 
	 */
	public void deleteUser(Utilisateur user) throws BusinessException;
	
	
}
