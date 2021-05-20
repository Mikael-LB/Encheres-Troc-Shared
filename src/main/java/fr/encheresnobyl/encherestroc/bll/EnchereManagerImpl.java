/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.dal.DAOFactory;
import fr.encheresnobyl.encherestroc.dal.EnchereDao;

/**
 * @author mlebris2021
 * Class which implements the method of EnchereManagerInt
 */
public class EnchereManagerImpl implements EnchereManagerInt {

	EnchereDao enchereDao = DAOFactory.getEnchereDAO();
	
	/**
	* {@inheritDoc}
	 * @throws BusinessException 
	*/
	@Override
	public ArticleVendu nouvelleEnchere(Enchere enchere) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		if (enchere.getUtilisateur()==null) {
			be.addError(CodesErreurBLL.NON_CONNECTE);
			throw be;
		}
		
		if (enchere.getMontantEnchere()<=enchere.getArticleVendu().getPrixVente()) {
			be.addError(CodesErreurBLL.ENCHERE_INFERIEURE);
		}
		
		if (enchere.getMontantEnchere()>enchere.getUtilisateur().getCredit()) {
			be.addError(CodesErreurBLL.CREDITS_INSUFFISANTS);
		}
		
		if (be.hasError()) {
			throw be;
		}
		
		enchereDao.insertEnchere(enchere);
		
		enchere.getArticleVendu().setListeEncheres(getListeEncheres(enchere.getArticleVendu()));
		return enchere.getArticleVendu();
		
	}
	
	public List<Enchere> getListeEncheres(ArticleVendu article) {
		
		return enchereDao.selectListeEncheres(article);
	}
	

}
