package ifsp.movietex.rating.servlet;

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
import ifsp.movietex.rating.dao.RatingDAO;


@WebServlet("/apt/rating/insertRating")
public class InsertRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String idFilmeStr = request.getParameter("idFilme");
		Integer idFilme = Integer.valueOf(idFilmeStr);
	
		String idUsuarioStr = request.getParameter("idUsuarioStr");
		Integer idUsuario = Integer.valueOf(idUsuarioStr);
		
		String comentario = request.getParameter("comentario");
		String notaStr = request.getParameter("nota");
		Double nota = Double.valueOf(notaStr);

		Connection conn = new ConnectionPostgress().getConnection();
		RatingDAO dao = new RatingDAO(conn);
		String msg = dao.insert(idFilme, idUsuario, nota, comentario);
		ResponseWrapper wrapper = new ResponseWrapper();
		if (msg.contains("Sucesso")) {
			wrapper.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			wrapper.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		wrapper.setData(msg);

		Gson gson = new Gson();

		String json = gson.toJson(gson);
		out.print(json);
		out.flush();
		
	}
}
