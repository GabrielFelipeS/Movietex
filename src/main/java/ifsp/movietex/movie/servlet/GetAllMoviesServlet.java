package ifsp.movietex.movie.servlet;

import java.io.IOException;
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

@WebServlet("/movie")
public class GetAllMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		
		String yearStr = request.getParameter("year");
		Integer year = yearStr != null ? Integer.valueOf(yearStr): null;
		
		String minRatingAverageStr = request.getParameter("minRatingAverage");
		Double minRatingAverage = minRatingAverageStr != null ? Double.valueOf(minRatingAverageStr): null;
		
		String maxRatingAverageStr = request.getParameter("maxRatingAverage");
		Double maxRatingAverage = maxRatingAverageStr != null ? Double.valueOf(maxRatingAverageStr): null;
		
		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		List<Movie> movies = dao.findBy(title, description, genre, director, year, minRatingAverage, maxRatingAverage);
		
		Gson gson = new Gson();
		String json = gson.toJson(movies);
	}
}
