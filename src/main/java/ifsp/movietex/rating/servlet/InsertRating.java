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
import ifsp.movietex.rating.dao.RatingDAO;


@WebServlet("/apt/rating/insertRating")
public class InsertRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String idFilmeStr = request.getParameter("idMovie");
		Integer idFilme = Integer.valueOf(idFilmeStr);
	
		String idUsuarioStr = request.getParameter("idUser");
		Integer idUsuario = Integer.valueOf(idUsuarioStr);
		
		String userName = request.getParameter("userName");
		
		
		String comentario = request.getParameter("comment");
		String notaStr = request.getParameter("note");
		Double nota = Double.valueOf(notaStr);

		Connection conn = new ConnectionPostgress().getConnection();
		RatingDAO dao = new RatingDAO(conn);
		String mensagem = dao.insert(idFilme, idUsuario, userName, nota, comentario);
	
		if (mensagem.contains("Sucesso")) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		Gson gson = new Gson();

		String json = gson.toJson(mensagem);
		out.print(json);
		out.flush();
	}
}
