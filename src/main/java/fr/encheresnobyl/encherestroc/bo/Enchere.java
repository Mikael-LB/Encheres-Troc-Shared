/**
 * 
 */
package fr.encheresnobyl.encherestroc.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:24:38
 */
public class Enchere implements Serializable {
	
	/**
	 * @author mlebris2021
	 * add serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attributes
	 */	
	LocalDateTime dateEnchere;
	int montantEnchere;
	
	
	
	 //--GETTERS--
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	
	//--SETTERS--
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	
	
}
