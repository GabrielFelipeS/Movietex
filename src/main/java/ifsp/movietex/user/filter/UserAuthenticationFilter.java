package ifsp.movietex.user.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AutenticacaoUsuario
 */
@WebFilter("/AutenticacaoUsuario")
public class UserAuthenticationFilter extends HttpFilter implements Filter {
    
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servRequest = (HttpServletRequest) request;
        HttpServletResponse servResponse = (HttpServletResponse) response;

        // Verifica se há uma sessão existente
        HttpSession session = servRequest.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("usuario") != null;

        if (loggedIn) {
            // Se estiver autenticado, continua a requisição
            chain.doFilter(servRequest, response);
        } else {
            // Caso contrário, redireciona para a página de login
        	
        	//TODO
            servResponse.sendRedirect(servRequest.getContextPath() + " ");
        }
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}




}
