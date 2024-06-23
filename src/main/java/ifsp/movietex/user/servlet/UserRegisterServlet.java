package ifsp.movietex.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.user.dao.UserDAO;
import ifsp.movietex.user.entity.DTOUser;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {

	
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
    	   writer.println("Cadastrado");
    	   System.out.println("Ok");
           response.sendRedirect("login.jsp");
       }else {
    	   writer.println("Erro no cadastro");
    	   System.out.println("erro");

       }

	}
	
	
	// TODO redirect
	
}