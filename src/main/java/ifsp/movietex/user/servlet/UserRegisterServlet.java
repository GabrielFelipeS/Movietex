package ifsp.movietex.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;
import ifsp.movietex.user.dao.UserDAO;
import ifsp.movietex.user.entity.DTOUser;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/registro")
public class UserRegisterServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registro.jsp");
        dispatcher.forward(request, response);
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		Connection conn = new ConnectionPostgress().getConnection();
		UserDAO userDao = new UserDAO(conn);
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

       boolean result = userDao.register(new DTOUser(name, email, password));
       if(result) {
    	   System.out.println("Ok");
    	   String Message = "Cadastrado com sucesso! Faça o login.";
	       response.sendRedirect("login.jsp?cadastroSucess=" + URLEncoder.encode(Message, "UTF-8"));
       }else {
    	   System.out.println("erro");
    	   String Message = "Usuário já existe.";
	       response.sendRedirect("registro.jsp?error=" + URLEncoder.encode(Message, "UTF-8"));
	       
       }

	}
	
	
	// TODO redirect
	
}
