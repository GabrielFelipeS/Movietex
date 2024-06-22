package ifsp.movietex.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/CadastroUsuario")
public class CadastroUsuario extends HttpServlet {
	
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");

        Usuario usuario = new Usuario(email, senha, nome);
        usuarioDao.cadastrar(usuario);
        
        response.sendRedirect(" ");
	}
	
	
	// TODO redirect
	
}
