package ifsp.movietex.user.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebFilter({"/painel.jsp", "/cadastro.jsp", "/edit.jsp"})
public class UserAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        
        HttpSession session = httpRequest.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("userLoggedIn") != null && (boolean) session.getAttribute("userLoggedIn");
        boolean isAdmin = session != null && session.getAttribute("isAdmin") != null && "admin".equals(session.getAttribute("isAdmin"));
        
        String requestURI = httpRequest.getRequestURI();
        System.out.println("Request URI: " + requestURI);
        System.out.println("Logged in: " + loggedIn);
        System.out.println("Admin: " + isAdmin);
        
        if (loggedIn) {
        	  chain.doFilter(request, response);
        } else {
        	String message = "Acesso exclusivo de administradores.";
        	httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?accessDenied=" + URLEncoder.encode(message, "UTF-8") );
              return;
        }

      
    }



    private boolean isRequestedPage(String requestURI, ArrayList<String> pages) {
        return pages.stream().anyMatch(page -> requestURI.equals(page) || requestURI.startsWith(page + "/"));
    }
}
