/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.encheresnobyl.encherestroc.bo.Retrait;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:30
 */
public class RetraitDaoJdbcImpl implements RetraitDao {
	
	public final static String SELECT_BY_ID_ARTICLE = "SELECT * FROM RETRAITS WHERE no_article = ?";

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
	
	

}
