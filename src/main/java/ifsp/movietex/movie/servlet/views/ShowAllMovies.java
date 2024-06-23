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

@WebServlet("/movies")
public class ShowAllMovies extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = new ConnectionPostgress().getConnection();
        MovieDAO dao = new MovieDAO(conn);
        List<Movie> movies = dao.findBy();
        req.setAttribute("movies", movies);
        RequestDispatcher dispatcher = req.getRequestDispatcher("movies.jsp");
        dispatcher.forward(req, resp);
    }

}
