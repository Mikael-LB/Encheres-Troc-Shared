package fr.encheresnobyl.encherestroc.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UtilisateurNonConnecte
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
		, 
		urlPatterns = { "/UtilisateurNonConnecte", 
						"/supprimer", "/DeleteUserServlet",
						"/DisconnectServlet", "/deconnexion", 
						"/ModifierProfil",
						"/Encherir","/Aquisition","/Ma-Vente","/EnchereEffectue",
						"/MonProfil", 
						"/VendreArticle"},
		servletNames = { 
				"DeleteUserServlet", 
				"DisconnectServlet", 
				"ModifierProfil", 
				"VendreArticle"
		})
public class UtilisateurNonConnecte implements Filter {

	private static final String CONNEXION_PAGE = "/connexion";

	/**
	 * Default constructor. 
	 */
	public UtilisateurNonConnecte() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession();

		if (null != session.getAttribute("utilisateur")) {
			chain.doFilter(request, response);
		}else {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.sendRedirect(httpRequest.getContextPath()+CONNEXION_PAGE);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
