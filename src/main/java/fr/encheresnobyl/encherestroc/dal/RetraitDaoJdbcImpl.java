/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Retrait;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:30
 */
public class RetraitDaoJdbcImpl implements RetraitDao {
	
	public final static String SELECT_BY_ID_ARTICLE = "SELECT * FROM RETRAITS WHERE no_article = ?";
	public final static String INSERT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";

	/**
	* {@inheritDoc}
	*/
	@Override
	public Retrait selectByIdArticle(int id) {
			
			Retrait retrait = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()){
				
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
				pStmt.setInt(1, id);
				
				ResultSet rs = pStmt.executeQuery();
				
				if (rs.next()) {
						retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return retrait;
	}
	
	public void insertNewRetrait(Retrait retrait, int idArticle) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setInt(1, idArticle);
			pStmt.setString(2, retrait.getRue());
			pStmt.setString(3, retrait.getCodePostal());
			pStmt.setString(4, retrait.getVille());
			
			pStmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
