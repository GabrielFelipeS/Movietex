package ifsp.movietex.user.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); 
	            System.out.println("Sessao invalidada");
	        }
			String Message = "Volte Sempre!";
			response.sendRedirect("index.jsp?msg=" + URLEncoder.encode(Message, "UTF-8"));

	    }

}
