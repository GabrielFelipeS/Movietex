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

@WebServlet("/api/movie/insert")
public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("titulo");
		System.out.println(title);
		String description = request.getParameter("descricao");
		String director = request.getParameter("diretor");
		String genre = request.getParameter("genero");

		String yearStr = request.getParameter("ano");
		System.out.println(yearStr);
		Integer year = yearStr != null ? Integer.valueOf(yearStr) : null;
		
		String poster = request.getParameter("poster");
		
		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		String mensagem = dao.insert(new DTOMovie(title, description, genre, director, year, poster));
		
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
