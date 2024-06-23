package ifsp.movietex.movie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;

@WebServlet("/api/movie/advancedSearch")
public class AdvancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("titulo");
		String description = request.getParameter("descricao");
		String director = request.getParameter("diretor");
		String genre = request.getParameter("genero");

		String yearStr = request.getParameter("ano");
		Integer year = yearStr != null ? Integer.valueOf(yearStr) : null;

		String minRatingAverageStr = request.getParameter("minRatingAverage");
		Double minRatingAverage = minRatingAverageStr != null ? Double.valueOf(minRatingAverageStr) : null;

		String maxRatingAverageStr = request.getParameter("maxRatingAverage");
		Double maxRatingAverage = maxRatingAverageStr != null ? Double.valueOf(maxRatingAverageStr) : null;

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		List<Movie> movies = dao.findBy(title, description, genre, director, year, minRatingAverage, maxRatingAverage);

		response.setStatus(HttpServletResponse.SC_OK);
		
		Gson gson = new Gson();

		String json = gson.toJson(movies);

		out.print(json);
		out.flush();
	}
}
