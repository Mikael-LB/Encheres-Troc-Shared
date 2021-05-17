/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Categorie;

/**
 * @author mlebris2021
 * class which implements the method from CategorieManager interface
 */
public class CategorieDaoJdbcImpl implements CategorieDao {
	
	private static final String SELECT_ALL_CATEGORIE = "SELECT * FROM categories;";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM categories WHERE no_categorie=? ;";

	/**
	 * {@inheritDoc}
	 * @throws SQLException 
	 */
	@Override
	public List<Categorie> getAllCategorie() throws SQLException {
		List<Categorie>lstCategorie = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			//Nous avons décidé de faire toutes les requêtes en PreparedStatement
			//We have decided to make all query by using the PreparedStatement method.
			//ResultSet rs = conn.createStatement().executeQuery(SELECT_ALL_CATEGORIE);
			
			PreparedStatement state = conn.prepareStatement(SELECT_ALL_CATEGORIE);
			ResultSet rs = state.executeQuery();
			Categorie categorie = null;
			while (rs.next()) {
				int noCategorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				categorie = new Categorie(noCategorie,libelle);
				lstCategorie.add(categorie);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCategorie;
	}
	
	public Categorie selectCategorieById(int no_categorie)throws SQLException {
		
		Categorie cat = new Categorie();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
			pStmt.setInt(1, no_categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				cat = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cat;
	}

}
