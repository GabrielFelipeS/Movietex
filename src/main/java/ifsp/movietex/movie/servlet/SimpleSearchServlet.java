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

@WebServlet("/api/movie/simpleSearch")
public class SimpleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("search").toLowerCase();
		String director = request.getParameter("search").toLowerCase();
		String genre = request.getParameter("search").toLowerCase();
		System.out.println(title);
		String yearStr = request.getParameter("search");
		Integer year = yearStr != null && !yearStr.matches("^[^0-9]+$") ? Integer.valueOf(yearStr) : null;

		String ratingAverageStr = request.getParameter("search");
		Double ratingAverage = ratingAverageStr != null && !yearStr.matches("^[^0-9.]+$")
				? Double.valueOf(ratingAverageStr)
				: null;

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		List<Movie> movies = dao.findWithAtLeastOneValue(title, genre, director, year, ratingAverage);

		Gson gson = new Gson();

		response.setStatus(HttpServletResponse.SC_OK);

		String json = gson.toJson(movies);

		out.print(json);
		out.flush();
	}

}
