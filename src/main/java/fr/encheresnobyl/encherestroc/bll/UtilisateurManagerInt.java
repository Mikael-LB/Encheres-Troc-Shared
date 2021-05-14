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
}
