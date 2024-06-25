package ifsp.movietex.user.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminAuthenticationFilter
 */
@WebFilter("/painel/*")
public class AdminAuthenticationFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		Object isAdmin = session.getAttribute("isAdmin");
		System.out.println(isAdmin);
		if (isAdmin == null || !((String) isAdmin).equals("admin")) {
			String message = "Acesso exclusivo de administradores.";
			session.setAttribute("semPermissao", message);
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?msg=" + URLEncoder.encode(message, "UTF-8"));
		} else {
			chain.doFilter(request, response);
		}
	}
}
