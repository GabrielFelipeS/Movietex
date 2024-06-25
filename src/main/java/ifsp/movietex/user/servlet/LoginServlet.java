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
import javax.servlet.http.HttpSession;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;
import ifsp.movietex.user.dao.UserDAO;
import ifsp.movietex.user.entity.DTOUser;

/**
 * Servlet implementation class LoginUsuario
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		Connection conn = new ConnectionPostgress().getConnection();
		UserDAO userDao = new UserDAO(conn);

		String email = request.getParameter("email");
		String senha = request.getParameter("password");

		String result = userDao.login(new DTOUser(null, email, senha));

		if (result != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isAdmin", result);
			session.setAttribute("email", email);
			session.setAttribute("userLoggedIn", true);

			if ((boolean) session.getAttribute("userLoggedIn")) {
				System.out.println("logado");
			}

			String redirectUrl = (String) session.getAttribute("redirectUrl");
			if (redirectUrl != null) {
				session.removeAttribute("redirectUrl");
				response.sendRedirect(redirectUrl);
				System.out.println("localizado");
			} else {
				System.out.println(result);

				String Message = "Bem vindo(a)!";
				response.sendRedirect("index.jsp?msg=" + URLEncoder.encode(Message, "UTF-8"));

			}

		} else {
			System.out.println("Não existe");
			String Message = "E-mail ou senha incorretos. Tente novamente.";
			response.sendRedirect("login.jsp?error=" + URLEncoder.encode(Message, "UTF-8"));

		}

	}

}
