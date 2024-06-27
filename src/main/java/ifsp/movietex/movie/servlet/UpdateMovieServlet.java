package ifsp.movietex.movie.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.movie.entity.DTOMovie;

/**
 * Servlet implementation class UpdateMovieServlet
 */
@WebServlet("/api/movie/update")
@MultipartConfig
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String idStr = request.getParameter("id");
		Integer id = idStr != null ? Integer.valueOf(idStr) : null;

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		System.out.println(description);
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");

		String yearStr = request.getParameter("year");
		Integer year = Integer.valueOf(yearStr);

		String durationStr = request.getParameter("duration");
		System.out.println(durationStr);

		Integer duration = Integer.valueOf(durationStr);
		System.out.println(duration);
		String poster = request.getParameter("poster");
		Part filePart = request.getPart("file"); // Recupera <input type="file" name="file">
		if (filePart != null) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

			InputStream fileContent = filePart.getInputStream();

			// Escreve o conteúdo do arquivo em um novo arquivo no servidor
			File uploads = new File(request.getServletContext().getRealPath("img") + "/capas");

			File file = new File(uploads, fileName);
			try (InputStream input = fileContent; OutputStream output = new FileOutputStream(file)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
			}
			poster = "./img/capas/" + fileName;
		}

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		String mensagem = dao.update(new DTOMovie(id, title, description, genre, director, year, duration, poster));

		response.sendRedirect(request.getContextPath() + "/painel");

	}

}
