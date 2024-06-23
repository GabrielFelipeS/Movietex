package ifsp.movietex.rating.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.base.db.ResponseWrapper;
import ifsp.movietex.rating.dao.RatingDAO;
import ifsp.movietex.rating.entity.Rating;

/**
 * Servlet implementation class FindRatingByMovie
 */
@WebServlet("/api/rating/find")
public class FindRatingsByMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String idFilmeStr = Objects.requireNonNull(request.getParameter("idFilme"), "ID do filme não pode ser nulo");
		Integer idFilme = Integer.valueOf(idFilmeStr);

		Connection conn = new ConnectionPostgress().getConnection();
		RatingDAO dao = new RatingDAO(conn);
		List<Rating> ratings = dao.findBy(idFilme);
		
		Gson gson = new Gson();
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setStatus(HttpServletResponse.SC_OK);
		wrapper.setData(ratings);

		String json = gson.toJson(wrapper);

		out.print(json);
		out.flush();
	}

}
