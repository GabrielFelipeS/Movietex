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

/**
 * Servlet implementation class FindAllDirectorsServlet
 */
@WebServlet("/api/movie/findAllDirectors")
public class FindAllDirectorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);

		List<String> directors = dao.findAllDirectors();
		
		Gson gson = new Gson();
		
		response.setStatus(HttpServletResponse.SC_OK);

		String json = gson.toJson(directors);

		out.print(json);
		out.flush();
	}
}
