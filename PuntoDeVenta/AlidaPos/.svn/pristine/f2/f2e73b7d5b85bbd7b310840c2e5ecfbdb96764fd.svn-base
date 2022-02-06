package com.alidasoftware.pos.filter;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alidasoftware.pos.bean.LoginBean;
import com.alidasoftware.pos.model.Modulo;

public class UrlAccessFilter implements Filter {

	private ServletContext sc;
	private FilterConfig fc;
	private LoginBean loginBean = null;
	private List<Modulo> modulos = null;
	private String userAuthenticated = "";
	private String userSession = "";
	private boolean inLogin = false;
		

	@Override
	public void destroy() {
		this.sc = null;
	    this.fc = null;
	    this.userAuthenticated = "";
	    this.inLogin = false;
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.sc = filterConfig.getServletContext();
		this.fc = filterConfig;
		this.userAuthenticated = "";
		this.inLogin = false;
	}
	
	private boolean checkForAccess(String path) {
		Modulo modulo = null;
		if (modulos == null) {
			return false;
		} else {
			for (int i = 0; i < modulos.size(); i++) {
				modulo = modulos.get(i);
				if (modulo.getPath() != null) {
					if (path.startsWith(modulo.getPath()) && modulo.getActivo()) {
						return true;
					}
				}
			}
			/*
			if (path.equals("/logout.xhtml")) {
				return true;
			}
			*/
			return false;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String scheme = httpRequest.getScheme();
		String serverName = httpRequest.getServerName();
		int serverPort = httpRequest.getServerPort();
		String contextPath = httpRequest.getContextPath();
		String servletPath = httpRequest.getServletPath();			
		String pathInfo = httpRequest.getPathInfo();
		String queryString = httpRequest.getQueryString();
		StringBuffer url = httpRequest.getRequestURL();

		//System.out.println("URI: " + servletPath);		
		
		HttpSession session = httpRequest.getSession();
		
		/*
		System.out.println("URI        : " + servletPath);
		System.out.println("URL        : " + url);
		System.out.println("SessionLog : " + userSession + " - Session: " + session.getId());
		System.out.println("Userlog    : " + userAuthenticated + " - User: " + session.getAttribute("usuario"));
		System.out.println("pathInfo   : " + pathInfo + " - queryString: " + queryString);		
		System.out.println("--------------------------------");
		*/				
		
		if (session != null && session.getAttribute("usuario") != null) {	
			if (userAuthenticated.equals("")) {
				userAuthenticated = (String) session.getAttribute("usuario");
			}		
			if (userSession.equals("")) {
				userSession = session.getId();
			}
			if (loginBean == null) {
				loginBean = (LoginBean) httpRequest.getSession().getAttribute("loginBean");
			}
			if ((modulos == null || (modulos != null && modulos.size() == 0)) && loginBean != null) {				
				//System.out.println("User    : " + loginBean.getUser());
				//System.out.println("UserName: " + loginBean.getUser().getClave());
				//System.out.println("Password: " + loginBean.getUser().getClaveacceso());
				modulos = loginBean.getUserModules();
				//System.out.println("Total Modulos: " + modulos.size());
			}
			// se verifican solo las URL que terminen en xhtml
			try {
				if (servletPath.endsWith("xhtml") && !servletPath.startsWith("/javax.faces.resource/")) {
					//System.out.println("Session: " + session.getId() + " - log: " + userAuthenticated + " - " + session.getAttribute("usuario"));					
					//System.out.println("URI: " + servletPath);
					/*
					if (modulos != null && modulos.size() > 0) {
						for (int i = 0; i < modulos.size(); i++) {
							if (modulos.get(i).getPath() != null) {
								System.out.println("\t\t" + modulos.get(i).getPath());
							}
						}
					}					
					*/
					if (servletPath.equals("/login.xhtml")) {
						String menuPath = scheme + "://" + serverName + ":" + serverPort + contextPath + "/menu.xhtml";						
						httpResponse.sendRedirect(menuPath);
					} else {
						boolean moduleAccess = checkForAccess(servletPath);
						if (!moduleAccess && !servletPath.equals("/menu.xhtml")) {
							//System.out.println("NO tiene acceso al modulo: " + servletPath);
							String menuPath = scheme + "://" + serverName + ":" + serverPort + contextPath + "/menu.xhtml";						
							httpResponse.sendRedirect(menuPath);
						} 
						if (moduleAccess && loginBean != null && !servletPath.equals("/menu.xhtml") && !servletPath.equals("/login.xhtml")) {
							loginBean.updateStatistics(servletPath);
						}
					}					
				}
			}  catch (Exception ex) {
				ex.printStackTrace();
			}
		
		} else {
			if (servletPath.equals("/login.xhtml") && !inLogin) {
				inLogin = true;
			}
			if (!inLogin) {			
				String loginPath = scheme + "://" + serverName + ":" + serverPort + contextPath + "/login.xhtml";
				httpResponse.sendRedirect(loginPath);
			}
		}
		try {
			filterChain.doFilter(request,response);
		} catch (ServletException e) {
		    if (e.getRootCause() instanceof ViewExpiredException) {
		        request.getRequestDispatcher("/login.xhtml").forward(request, response);
		    } else {
		        throw e;
		    }
		}
	}

}
