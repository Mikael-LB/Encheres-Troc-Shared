/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:26:40
 */
public class UtilisateurDaoJdbcImpl implements UtilisateurDao{

	private static final String SELECT_ALL="SELECT * FROM UTILISATEURS";
	private static final String SELECT_BY_PSEUDO="SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_MAIL="SELECT * FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_BY_IDENTIFIANT="SELECT * FROM UTILISATEURS WHERE pseudo = ? or email = ?";
	private static final String SELECT_BY_ID="SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public List<Utilisateur> selectAll() {

		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), 
															rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),
															rs.getString("telephone"),rs.getString("rue"), rs.getString("code_postal"),
															rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"), 
															rs.getInt("administrateur")==1);
				listeUtilisateurs.add(utilisateur);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeUtilisateurs;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		
		Utilisateur utilisateur = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pStmt.setString(1, pseudo);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), 
															rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),
															rs.getString("telephone"),rs.getString("rue"), rs.getString("code_postal"),
															rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"), 
															rs.getInt("administrateur")==1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Boolean selectByMail(String mail) {
		
		boolean existsUtilisateur= false;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_MAIL);
			pStmt.setString(1, mail);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				existsUtilisateur=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return existsUtilisateur;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Utilisateur selectByIdentifiant(String identifiant) {
		
		Utilisateur utilisateur = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_IDENTIFIANT);
			pStmt.setString(1, identifiant);
			pStmt.setString(2, identifiant);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), 
															rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),
															rs.getString("telephone"),rs.getString("rue"), rs.getString("code_postal"),
															rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"), 
															rs.getInt("administrateur")==1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur selectById(int id) {
		
		Utilisateur utilisateur = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), 
						rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),
						rs.getString("telephone"),rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"), 
						rs.getInt("administrateur")==1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}
	
}
