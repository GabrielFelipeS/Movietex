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

@WebServlet("/painel/api/movie/insert")
@MultipartConfig
public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		
		String description = request.getParameter("description");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");

		String yearStr = request.getParameter("year");
		Integer year = Integer.valueOf(yearStr);

		String durationStr = request.getParameter("duration");
		Integer duration = Integer.valueOf(durationStr);
		
		System.out.println("description " + description);
		Part filePart = request.getPart("file"); // Recupera <input type="file" name="file">
	      
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		
		InputStream fileContent = filePart.getInputStream();

		// Escreve o conteúdo do arquivo em um novo arquivo no servidor
		File uploads = new File(request.getServletContext().getRealPath("img")+"/capas");
		
		File file = new File(uploads, fileName);
		try (InputStream input = fileContent; OutputStream output = new FileOutputStream(file)) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		}
		
		String poster = "./img/capas/" + fileName;

		Connection conn = new ConnectionPostgress().getConnection();
		MovieDAO dao = new MovieDAO(conn);
		String mensagem = dao.insert(new DTOMovie(title, description, genre, director, year, duration, poster));

		if (mensagem.contains("Sucesso")) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		response.sendRedirect(request.getContextPath() + "/painel");
	}

}
