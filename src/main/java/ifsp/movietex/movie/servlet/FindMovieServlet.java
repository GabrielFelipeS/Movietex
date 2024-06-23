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
import ifsp.movietex.base.db.ResponseWrapper;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;

@WebServlet("/api/movie/find")
public class FindMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		ResponseWrapper wrapper = new ResponseWrapper();
		PrintWriter out = response.getWriter();

		String idString = request.getParameter("id");
		
		if(idString != null) {
			Integer id = Integer.valueOf(idString);

			Connection conn = new ConnectionPostgress().getConnection();
			MovieDAO dao = new MovieDAO(conn);
			Movie movie = dao.findBy(id);

			wrapper.setStatus(movie != null? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
			wrapper.setData(movie);
		
		} else {
			wrapper.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			wrapper.setData("ID não pode ser nulo");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(wrapper);
		out.print(json);
		out.flush();
	
	
	}

}
