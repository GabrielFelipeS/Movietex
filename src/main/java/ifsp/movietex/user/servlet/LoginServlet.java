package ifsp.movietex.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ifsp.movietex.usuario.dao.UsuarioDAO;

/**
 * Servlet implementation class LoginUsuario
 */
@WebServlet("/LoginUsuario")
public class LoginServlet extends HttpServlet {

    private UserDAO usuarioDAO = new UserDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = usuarioDAO.findByEmail(email);
		if(usuario != null && usuario.getSenha().equals(senha)) {
			HttpSession session = request.getSession();
			if(usuario.isAdmin()) {
				
			}else {
				//não admin
			}
		}else {
			//se login falhar
			response.sendRedirect("");
		}
		
		
	}

}
