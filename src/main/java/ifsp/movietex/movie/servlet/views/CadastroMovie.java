package ifsp.movietex.movie.servlet.views;

import java.io.IOException;
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

@WebServlet("/painel/cadastro")
public class CadastroMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = new ConnectionPostgress().getConnection();
        MovieDAO dao = new MovieDAO(conn);
        
        List<String> directors = dao.findAllDirectors();
        request.setAttribute("directors", directors);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro.jsp");
        dispatcher.forward(request, response);
	}

}
