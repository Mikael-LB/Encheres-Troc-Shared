package fr.encheresnobyl.encherestroc.messages;

import java.util.ResourceBundle;

/**
 * Class to get the text message for a given error code
 *
 */
public class LecteurMessage {
	
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("fr.encheresnobyl.encherestroc.messages.messages_erreur");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageErreur(int code) {
		String message = "";
		
		try {
			if(rb != null) {
				message = rb.getString(String.valueOf(code));
			} else {
				message = "Code erreur inexistant";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Erreur inconnue : " + e.getMessage();
		}
		
		return message;
	}

}
