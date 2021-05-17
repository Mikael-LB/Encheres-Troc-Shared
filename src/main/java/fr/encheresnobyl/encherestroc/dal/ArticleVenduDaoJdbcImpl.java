/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:05
 */
public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao {

	private static final String PARAM_ENCHERES_REMPORTEES = "enchereRemporte";
	private static final String PARAM_ENCHERES_UTILISATEUR = "enchereUtilisateur";
	private static final String PARAM_ENCHERES_OUVERTES = "enchereOuverte";
	
	private static final String SELECT_ALL_DISPO = "SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE()";
	private static final String SELECT_BY_NOM_DISPO = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? AND date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE()";
	private static final String SELECT_BY_NOM_AND_CATEGORIE_DISPO = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? AND nom_article LIKE ? AND date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE()";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectAllDispo() {
		
		List<ArticleVendu> listeArticleVendus=new ArrayList<ArticleVendu>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_DISPO);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"));
				
				listeArticleVendus.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeArticleVendus;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public List<ArticleVendu> selectDispoByNom(String nom) {
		
		List<ArticleVendu> listeArticleVendus=new ArrayList<ArticleVendu>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NOM_DISPO);
			pStmt.setString(1, "%"+nom+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"));
				
				listeArticleVendus.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeArticleVendus;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<ArticleVendu> selectDispoByNomAndCategorie(String nom, int noCategorie) {
		
		List<ArticleVendu> listeArticleVendus=new ArrayList<ArticleVendu>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NOM_AND_CATEGORIE_DISPO);
			pStmt.setInt(1, noCategorie);
			pStmt.setString(2, "%"+nom+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"));
				
				listeArticleVendus.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeArticleVendus;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<ArticleVendu> selectEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> parametres) {
		
		StringBuilder requeteBuilder = new StringBuilder("SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?");
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();
		boolean multichoice = false;
		
		if(noCategorie!=0) {
			requeteBuilder.append(" AND no_categorie= ?");
		}
		
		if(!parametres.isEmpty()) {
			requeteBuilder.append(" AND (");
			
			if (parametres.contains(PARAM_ENCHERES_OUVERTES)) {
				requeteBuilder.append(" date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE()");
				multichoice=true;
			}
			if (parametres.contains(PARAM_ENCHERES_UTILISATEUR)) {
				if (multichoice) {
					requeteBuilder.append(" OR");
				}
				requeteBuilder.append(" no_article IN (SELECT art.no_article FROM ARTICLES_VENDUS art INNER JOIN ENCHERES e ON art.no_article = e.no_article WHERE e.no_utilisateur = ?)");
				multichoice=true;
			}
			if (parametres.contains(PARAM_ENCHERES_REMPORTEES)) {
				if (multichoice) {
					requeteBuilder.append(" OR");
				}
				requeteBuilder.append(" no_article IN (SELECT art.no_article FROM ARTICLES_VENDUS art INNER JOIN ENCHERES e ON art.no_article = e.no_article WHERE e.no_utilisateur = ? AND e.montant_enchere = art.prix_vente) AND date_fin_encheres<GETDATE()");
			}
			
			requeteBuilder.append(")");
		}
		
		String requete = requeteBuilder.toString();
		
		System.out.println(requete);
			
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(requete);
			int IndexStmt=1;
			
			pStmt.setString(IndexStmt++, "%"+motCle+"%");
			
			if(noCategorie!=0) {
				pStmt.setInt(IndexStmt++, noCategorie);
			}
			
			if(parametres != null || !parametres.isEmpty()) {
				

				if (parametres.contains(PARAM_ENCHERES_UTILISATEUR)) {
					pStmt.setInt(IndexStmt++, noUtilisateur);
				}
				if (parametres.contains(PARAM_ENCHERES_REMPORTEES)) {
					pStmt.setInt(IndexStmt++, noUtilisateur);
				}
			}
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"));
				
			listeArticleVendus.add(article);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listeArticleVendus;
	}

}
