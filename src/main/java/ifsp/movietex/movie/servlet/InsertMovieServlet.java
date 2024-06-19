package ifsp.movietex.movie.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.DTOMovie;

/**
 * Servlet implementation class InsertMovieServlet
 */
@WebServlet("/api/movie/insert")
public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		
		String yearStr = request.getParameter("year");
		Integer year = yearStr != null ? Integer.valueOf(yearStr): null;
		
		
		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		String msg = dao.insert(new DTOMovie(title, description, genre, director, year));
		
		session.setAttribute("msg", msg);
	}

}
