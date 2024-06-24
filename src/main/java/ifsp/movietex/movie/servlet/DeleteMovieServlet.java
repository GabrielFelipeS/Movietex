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

/**
 * Servlet implementation class DeleteMovieServlet
 */
@WebServlet("/api/movie/delete")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Integer id = Integer.valueOf(request.getParameter("id"));

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		Boolean success = dao.deleteBy(id);

		Gson gson = new Gson();

		response.setStatus(success? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);

		String json = gson.toJson(success);

		out.print(json);
		out.flush();

	}

}
