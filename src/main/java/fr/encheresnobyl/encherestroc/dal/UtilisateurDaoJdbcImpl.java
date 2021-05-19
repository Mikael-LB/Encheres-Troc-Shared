/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.encheresnobyl.encherestroc.bll.BusinessException;
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
	private static final String INSERT_UTILISATEUR=
			"INSERT INTO utilisateurs"
			+ " (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
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

	/**
	 * @author mlebris2021
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur insert(Utilisateur user) {
		
		if (user == null) {
			BusinessException be = new BusinessException();
			be.addError(CodesErrorDAL.INSERT_OBJECT_NULL);
			throw be;
		}
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			
			try {
			PreparedStatement state = conn.prepareStatement(
					INSERT_UTILISATEUR,
					PreparedStatement.RETURN_GENERATED_KEYS);
			//(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
			state.setString(1, user.getPseudo());
			state.setString(2, user.getNom());
			state.setString(3, user.getPrenom());
			state.setString(4, user.getEmail());
			state.setString(5, user.getTelephone());
			state.setString(6, user.getRue());
			state.setString(7, user.getCodePostal());
			state.setString(8, user.getVille());
			state.setString(9, user.getMotDePasse());
			state.setInt(10, user.getCredit());
			state.setBoolean(11, user.isAdministrateur());

			state.executeUpdate();
			ResultSet rs = state.getGeneratedKeys();
			
			if (rs.next()) {
				user.setNumeroUtilisateur(rs.getInt(1));
			}
			
			conn.commit();
			}catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
