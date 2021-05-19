/**
 * 
 */
package fr.encheresnobyl.encherestroc.servlets.utils;

import java.time.LocalDate;
import java.util.List;

import fr.encheresnobyl.encherestroc.bll.BusinessException;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc-Shared - v1.0
 * @date 18 mai 2021 - 19:47:39
 */
public class ValidateurParse {
	
	private final BusinessException be = new BusinessException();
	
	public boolean validerInteger(String entierAValider, int codeErreur) {
		try {
			Integer.parseInt(entierAValider);
			return true;
		}catch (Exception e) {
			be.addError(codeErreur);
			return false;
		}
	}
	
	public boolean validerDate(String dateAValider, int codeErreur) {
		try {
			LocalDate.parse(dateAValider);
			return true;
		} catch (Exception e) {
			be.addError(codeErreur);
			return false;
		}
	}
	
	/**
	 * Getter pour be.
	 * @return the be
	 */
	public BusinessException getBe() {
		return be;
	}
	

}
