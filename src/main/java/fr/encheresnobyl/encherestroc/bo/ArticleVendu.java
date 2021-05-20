/**
 * 
 */
package fr.encheresnobyl.encherestroc.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.encheresnobyl.encherestroc.bll.CategorieManagerImpl;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerInt;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 09:06:05
 */
public class ArticleVendu implements Serializable{
	

	/**
	 * @author mlebris2021
	 * add serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attributes
	 */
	int noArticle;
	String nomArticle;
	String description;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	int miseAPrix;
	int prixVente;
	Retrait pointRetrait;
	List<Enchere> listeEncheres;
	Utilisateur utilisateur;
	Categorie categorie;
	boolean etatVente;
	
	/**
	 * Empty constructor
	 */
	public ArticleVendu() {
		
	}
	/**
	 * Constructor
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 */
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
		this.noArticle=noArticle;
		this.prixVente=prixVente;
	}
	
	public ArticleVendu( String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, Retrait retrait, int idCategorie) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
		this.pointRetrait=retrait;
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		this.categorie =categorieManager.selectById(idCategorie);
	}
	
	public int getPrixArticle() {
		
		int prixArticle=this.miseAPrix;
		
		if (this.prixVente!=0) {
			prixArticle=this.prixVente;
		}	
		return prixArticle;	
	}
	
	// -- GETTERS --
	
	/**
	 * Constructeur.
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 */
	public int getNoArticle() {
		return noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public boolean isEtatVente() {
		return etatVente;
	}
	public Retrait getPointRetrait() {
		return pointRetrait;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public Enchere getMeilleurEnchere() {
		Enchere meilleurEnchere=new Enchere();
		if(listeEncheres!=null) {
			for (Enchere enchere : listeEncheres) {
				if(enchere.getMontantEnchere()==this.getPrixVente()) {
					meilleurEnchere=enchere;
				}
			}
		}
		return meilleurEnchere;		
	}
	
	
	
	// -- SETTERS --
	
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}
	public void setPointRetrait(Retrait pointRetrait) {
		this.pointRetrait = pointRetrait;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + "]";
	}
	
}
