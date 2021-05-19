/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlebris2021
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Integer> lstErrorCodes;

	/**
	 * Constructor
	 * @param lstErrorCodes
	 */
	public BusinessException() {
		this.lstErrorCodes = new ArrayList<>();
	}

	/**
	 * Getter
	 * @return the lstErrorCodes : List<Integer>
	 */
	public List<Integer> getLstErrorCodes() {
		return lstErrorCodes;
	}
	
	/**
	 * Method to add an error code to the list
	 * @param errorCode
	 */
	public void addError(int errorCode) {
		this.lstErrorCodes.add(errorCode);
	}
	
	/**
	 * Method to knox if the list of errors is empty or not
	 * @return true if there is at one or more error code
	 */
	public boolean hasError() {
		return !this.lstErrorCodes.isEmpty();
	}
	
	
}
