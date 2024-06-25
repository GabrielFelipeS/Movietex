package ifsp.movietex.movie.servlet.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/painel/edit/*")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo().substring(1);
		Integer id = Integer.valueOf(path);
		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO daoMovie = new MovieDAO(conn);

		Movie movie = daoMovie.findBy(id);
		request.setAttribute("movie", movie);
		
		 List<String> directors = daoMovie.findAllDirectors();
	     request.setAttribute("directors", directors);
	        
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}

}
