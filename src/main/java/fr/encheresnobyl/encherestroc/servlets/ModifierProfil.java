package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.messages.LecteurMessage;

/**
 * Servlet implementation class ModifieProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfil() {
        super();
        // TODO Auto-generated constructor stub1
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
		Utilisateur utilisateurModif;
		Utilisateur utilisateurSession;
		HttpSession session=request.getSession() ;
		String pseudo = request.getParameter("pseudo");
		String userName = request.getParameter("userName");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String passwd = request.getParameter("passwd");
		String passwdConfirm = request.getParameter("passwdConfirm");
		String passwdVerif = request.getParameter("passwdVerif");
		

		//TODO to replace the method with long parameter list
		//Map<String, String[]> parameterMap = request.getParameterMap();

			try {
				
				utilisateurSession=(Utilisateur) session.getAttribute("utilisateur");
				
		
				utilisateurManager.checkUpdateParam( //THROWS BE
						utilisateurSession,
						pseudo,
						userName,
						firstname,
						email,
						phone,
						street,
						postalCode,
						city,
						passwd,
						passwdConfirm);				
					
			
			utilisateurModif = new Utilisateur(pseudo, userName, firstname, email, phone, street, postalCode, city, passwd);
			System.out.println(utilisateurModif);

			System.out.println("passage dans Post Servlet");
			utilisateurManager.modifierProfil(utilisateurSession,utilisateurModif,passwdVerif);
			
			utilisateurModif=utilisateurManager.selectById(utilisateurSession.getNumeroUtilisateur());
			
			session.setAttribute("utilisateur", utilisateurModif);
			
			response.sendRedirect(request.getContextPath()+"/Mon-Profil");
			
			} catch (BusinessException be) {
				request.setAttribute("errorList", be.getLstErrorCodes());
				request.setAttribute("messageReader", new LecteurMessage());
				request.getRequestDispatcher("/WEB-INF/front-office-user/modifierProfil.jsp").forward(request, response);
			}

	}

}
