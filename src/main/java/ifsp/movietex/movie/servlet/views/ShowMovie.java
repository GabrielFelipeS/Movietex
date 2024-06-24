package ifsp.movietex.movie.servlet.views;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.Movie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/movie/*")
public class ShowMovie extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo().substring(1);
        Integer id = null;
        if (path.matches("^[^0-9]+$")) {
        	 response.sendRedirect(getCorrectRedirect(path));
             return;
        } else {
            id = Integer.parseInt(path);
            
            Connection conn = new ConnectionPostgress().getConnection();
            MovieDAO dao = new MovieDAO(conn);
            Movie movie = dao.findBy(id);
            
            if(movie == null) {
            	 response.sendRedirect("../movies");
            } else {
                request.setAttribute("movie", movie);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/interna.jsp");
                dispatcher.forward(request, response);
            }
            
        
        }

    }

	private String getCorrectRedirect(String path) {
		return path.matches("^movies$")? "../movies" : "../home";
	}

}
