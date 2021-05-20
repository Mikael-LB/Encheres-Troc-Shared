/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:17
 */
public class EnchereDaoJdbcImpl implements EnchereDao {

	private static final String INSERT="INSERT INTO ENCHERES VALUES(?,?,?,?)";
	private static final String UPDATE_CREDIT="UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur=?";
	private static final String SELECT_DEUXIEME_ENCHERE_AND_UTILISATEUR="SELECT u.no_utilisateur, u.credit, e.montant_enchere FROM UTILISATEURS u INNER JOIN ENCHERES e ON u.no_utilisateur=e.no_utilisateur WHERE e.no_article=? AND montant_enchere IN (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article=? AND montant_enchere<?)";
	private static final String UPDATE_PRIX_ARTICLE="UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	private static final String SELECT = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY montant_enchere DESC";
	
	
	/**
	* {@inheritDoc}
	 * @return 
	*/
	@Override
	public void insertEnchere(Enchere enchere) {
			
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
			cnx.setAutoCommit(false);
			
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			pStmt.setInt(2, enchere.getMontantEnchere());
			pStmt.setInt(3, enchere.getArticleVendu().getNoArticle());
			pStmt.setInt(4, enchere.getUtilisateur().getNumeroUtilisateur());
			pStmt.executeUpdate();
			
			enchere.getUtilisateur().setCredit(enchere.getUtilisateur().getCredit()-enchere.getMontantEnchere());
			PreparedStatement pStmt2 = cnx.prepareStatement(UPDATE_CREDIT);
			pStmt2.setInt(1, enchere.getUtilisateur().getCredit());
			pStmt2.setInt(2, enchere.getUtilisateur().getNumeroUtilisateur());
			pStmt2.executeUpdate();
			
			PreparedStatement pStmt3 = cnx.prepareStatement(SELECT_DEUXIEME_ENCHERE_AND_UTILISATEUR);
			pStmt3.setInt(1, enchere.getArticleVendu().getNoArticle());
			pStmt3.setInt(2, enchere.getArticleVendu().getNoArticle());
			pStmt3.setInt(3, enchere.getMontantEnchere());
			
			ResultSet rs = pStmt3.executeQuery();
			
			Utilisateur ancienEncherisseur=null;
			
			if (rs.next()) {
				ancienEncherisseur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getInt("credit"));
				ancienEncherisseur.setCredit(ancienEncherisseur.getCredit()+rs.getInt("montant_enchere"));				

				PreparedStatement pStmt4 = cnx.prepareStatement(UPDATE_CREDIT);
				pStmt4.setInt(1, ancienEncherisseur.getCredit());
				pStmt4.setInt(2, ancienEncherisseur.getNumeroUtilisateur());
				pStmt4.executeUpdate();
			}
			
			PreparedStatement pStmt5 = cnx.prepareStatement(UPDATE_PRIX_ARTICLE);
			pStmt5.setInt(1, enchere.getMontantEnchere());
			pStmt5.setInt(2, enchere.getArticleVendu().getNoArticle());
			pStmt5.executeUpdate();

			cnx.commit();
			cnx.setAutoCommit(true);
			
			} catch (SQLException e) {
				cnx.rollback();
				cnx.setAutoCommit(true);
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	/**
	* {@inheritDoc}
	*/
	@Override
	public List<Enchere> selectListeEncheres(ArticleVendu article) {

		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT);
			pStmt.setInt(1, article.getNoArticle());
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), DAOFactory.getUtilisateurDAO().selectById(rs.getInt("no_utilisateur")), article);
				listeEnchere.add(enchere);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEnchere;
	}

}
