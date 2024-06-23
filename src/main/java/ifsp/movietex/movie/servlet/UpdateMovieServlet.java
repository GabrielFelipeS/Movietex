package ifsp.movietex.movie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.DTOMovie;

/**
 * Servlet implementation class UpdateMovieServlet
 */
@WebServlet("/api/movie/update")
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");

		String yearStr = request.getParameter("year");
		Integer year = yearStr != null ? Integer.valueOf(yearStr) : null;

		String minRatingAverageStr = request.getParameter("minRatingAverage");
		Double minRatingAverage = minRatingAverageStr != null ? Double.valueOf(minRatingAverageStr) : null;

		String maxRatingAverageStr = request.getParameter("maxRatingAverage");
		Double maxRatingAverage = maxRatingAverageStr != null ? Double.valueOf(maxRatingAverageStr) : null;

		String poster = request.getParameter("poster");
		
		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		String mensagem = dao.insert(new DTOMovie(title, description, genre, director, year, poster));

		Gson gson = new Gson();

		
		response.setStatus(mensagem.contains("Sucesso") ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);

		String json = gson.toJson(mensagem);

		out.print(json);
		out.flush();

	}

}
