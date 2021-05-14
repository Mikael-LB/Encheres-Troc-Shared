/**
 * 
 */
package fr.encheresnobyl.encherestroc.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mlebris2021
 * class which represent a Categorie
 */
public class Categorie implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attributes
	 */
	private Integer noCategorie;
	private String libelle;
	//do we really need this attribute ?? There is an * on the diagram but for what.
	private List<ArticleVendu> lstArticleVendu = new ArrayList<ArticleVendu>();
	
	/**
	 * Constructor
	 */
	public Categorie() {
		super();
	}
	
	/**
	 * Constructor
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this();
		this.libelle = libelle;
	}
	
	/**
	 * Constructor
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(Integer noCategorie, String libelle) {
		this(libelle);
		this.noCategorie = noCategorie;
	}

	/**
	 * Method to add an ArticleVendu to the List of ArticleVendu
	 * @param articleVendu
	 */
	public void addArticleVendu(ArticleVendu articleVendu) {
		this.lstArticleVendu.add(articleVendu);
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getLstArticleVendu() {
		return lstArticleVendu;
	}

	public void setLstArticleVendu(List<ArticleVendu> lstArticleVendu) {
		this.lstArticleVendu = lstArticleVendu;
	}

	/**
	 * Method toString for a Categorie
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", lstArticleVendu=");
		builder.append(lstArticleVendu);
		builder.append("]");
		return builder.toString();
	}
	
	
	

	
	

	
	
}
