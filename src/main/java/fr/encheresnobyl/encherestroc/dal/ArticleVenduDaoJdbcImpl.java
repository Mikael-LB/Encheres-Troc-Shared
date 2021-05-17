/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bll.CategorieManagerImpl;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerInt;
import fr.encheresnobyl.encherestroc.bll.RetraitManagerImpl;
import fr.encheresnobyl.encherestroc.bll.RetraitManagerInt;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Retrait;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:05
 */
public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao {

	private static final String PARAM_ENCHERES_REMPORTEES = "encheresRemportees";
	private static final String PARAM_ENCHERES_UTILISATEUR = "encheresUtilisateur";
	private static final String PARAM_ENCHERES_OUVERTES = "encheresOuvertes";
	
	private static final String PARAM_VENTE_EN_COURS = "ventesEnCours";
	private static final String PARAM_VENTE_TERMINEES = "ventesNonDebutees";
	private static final String PARAM_VENTE_NON_DEBUTEES = "ventesTerminees";
	
	private static final String SELECT_ARTICLE_BY_ID="SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";

	
	
	public List<ArticleVendu> selectEncheres(String motCle, int noCategorie){
		
		StringBuilder requeteBuilder = new StringBuilder("SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE() AND nom_article LIKE ?");
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();
		
		if(noCategorie!=0) {
			requeteBuilder.append(" AND no_categorie= ?");
		}
		String requete = requeteBuilder.toString();
		

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(requete);
			int IndexStmt=1;
			
			pStmt.setString(IndexStmt++, "%"+motCle+"%");
			
			if(noCategorie!=0) {
				pStmt.setInt(IndexStmt++, noCategorie);
			}
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"), rs.getInt("prix_vente"));
				
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
														rs.getInt("prix_initial"), rs.getInt("prix_vente"));
				
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
	public List<ArticleVendu> selectVentes(String motCle, int noCategorie, int noUtilisateur, List<String> parametres) {
		StringBuilder requeteBuilder = new StringBuilder("SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=? AND nom_article LIKE ?");
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();
		boolean multichoice = false;
		
		if(noCategorie!=0) {
			requeteBuilder.append(" AND no_categorie= ?");
		}
		
		if(!parametres.isEmpty()) {
			requeteBuilder.append(" AND (");
			
			if (parametres.contains(PARAM_VENTE_EN_COURS)) {
				requeteBuilder.append(" date_debut_encheres<GETDATE() AND date_fin_encheres>GETDATE()");
				multichoice=true;
			}
			if (parametres.contains(PARAM_VENTE_NON_DEBUTEES)) {
				if (multichoice) {
					requeteBuilder.append(" OR");
				}
				requeteBuilder.append(" date_debut_encheres>GETDATE()");
				multichoice=true;
			}
			if (parametres.contains(PARAM_VENTE_TERMINEES)) {
				if (multichoice) {
					requeteBuilder.append(" OR");
				}
				requeteBuilder.append(" date_fin_encheres<GETDATE()");
			}
			
			requeteBuilder.append(")");
		}
		
		String requete = requeteBuilder.toString();
		
		System.out.println(requete);
			
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(requete);
			int IndexStmt=1;
			
			pStmt.setInt(IndexStmt++, noUtilisateur);
			pStmt.setString(IndexStmt++, "%"+motCle+"%");
			
			if(noCategorie!=0) {
				pStmt.setInt(IndexStmt++, noCategorie);
			}
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
														rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
														rs.getInt("prix_initial"), rs.getInt("prix_vente"));
				
			listeArticleVendus.add(article);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listeArticleVendus;
	}
	
	
	public ArticleVendu selectArticleById(int id) {
		
		ArticleVendu articleVendu = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				articleVendu = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"));
			}
			
			CategorieManagerInt categorieManager = new CategorieManagerImpl();
			articleVendu.setCategorie(categorieManager.selectById(rs.getInt("no_categorie")));
			
			RetraitManagerInt retraitManager = new RetraitManagerImpl();
			articleVendu.setPointRetrait(retraitManager.selectByIdArticle(id));
			
			UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
			articleVendu.setUtilisateur(utilisateurManager.selectById(rs.getInt("no_utilisateur")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleVendu;
	}

}
