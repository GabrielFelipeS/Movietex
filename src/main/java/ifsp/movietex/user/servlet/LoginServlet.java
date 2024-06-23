package ifsp.movietex.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.user.dao.UserDAO;
import ifsp.movietex.user.entity.DTOUser;

/**
 * Servlet implementation class LoginUsuario
 */
@WebServlet("/LoginUser")
public class LoginServlet extends HttpServlet {



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		Connection conn = new ConnectionPostgress().getConnection();
		UserDAO userDao = new UserDAO(conn);
		
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		boolean result = userDao.login(new DTOUser(null, email, senha));
		
		if(result) {
			HttpSession session = request.getSession();
			if(result) {
		    	   writer.println("localizado");
		       }else {
		    	   writer.println("Não existe");

		       }
			//se login falhar
			response.sendRedirect("");
		}
		
		
	}

}
