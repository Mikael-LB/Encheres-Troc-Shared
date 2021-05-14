/**
 * 
 */
package fr.encheresnobyl.encherestroc.bo;

import java.io.Serializable;

/**
 * @author rkerhir2021
 *
 */
public class Utilisateur implements Serializable{
	
	/**
	 * @author mlebris2021
	 * add serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attributes
	 */
	private int numeroUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	
	
	/**
	 * @param administrateur
	 * @param numeroUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(int numeroUtilisateur, String pseudo, String nom, String prenom,
			String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		this(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse, credit);
		setAdministrateur(administrateur);
		setNumeroUtilisateur(numeroUtilisateur);		
	}
	
	/**
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit) {
		this();
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setMotDePasse(motDePasse);
		setCredit(credit);
	}
	
	
	
	/**
	 * @return the administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}

	/**
	 * @return the numeroUtilisateur
	 */
	public int getNumeroUtilisateur() {
		return numeroUtilisateur;
	}

	/**
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * @param numeroUtilisateur the numeroUtilisateur to set
	 */
	public void setNumeroUtilisateur(int numeroUtilisateur) {
		this.numeroUtilisateur = numeroUtilisateur;
	}

	public Utilisateur() {
		super();
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public int getCredit() {
		return credit;
	}
	
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * @param codePostal2 the codePostal to set
	 */
	public void setCodePostal(String codePostal2) {
		this.codePostal = codePostal2;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Utilisateur [numeroUtilisateur=" + numeroUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom
				+ ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit="
				+ credit + ", administrateur=" + administrateur + "]";
	}
	
}
