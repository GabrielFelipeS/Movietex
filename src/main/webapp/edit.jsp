<%--
  Created by IntelliJ IDEA.
  User: Micael Hernandes
  Date: 22/06/2024
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="ifsp.movietex.movie.entity.Movie"%>
<%@ page import="java.util.List" %>
<html>
<head>
<jsp:include page="head.jsp" />
<title>Editar Filme</title>
</head>
<body>

	<%
	Movie movie = (Movie) request.getAttribute("movie");
	%>

	<jsp:include page="header.jsp" />

	<main class="w-full  flex flex-col justify-center items-center">
		<div>
			<h2 class="text-4xl font-bold text-primary p-5">Editar Filme</h2>
		</div>
		<form action="" method=""
			class="w-[550px]  justify-around rounded-lg flex flex-col bg-primary p-4 gap-3"
			enctype="multipart/form-data">
			<label for="name" class="text-white">Nome do filme</label> <input
				required type="text" value="<%=movie.getTitle()%>" name="name"
				id="name" class="rounded-lg pl-3 h-[30px] outline-none"> <label
				for="duration" class="text-white">Duração</label> <input required
				type="text" name="duration" value="<%=movie.getDuration()%>"
				id="duration" class="rounded-lg pl-3 h-[30px] outline-none">
			<label for="duration" class="text-white">Gênero</label> <select
				required name="genre" id="genre" value="<%=movie.getGenre()%>"
				class="rounded-lg pl-3 h-[30px] outline-none">
				<option value="<% movie.getGenre();%>">Selecione um gênero</option>
				<option value="Ação">Ação</option>
				<option value="Aventura">Aventura</option>
				<option value="Comédia">Comédia</option>
				<option value="Drama">Drama</option>
				<option value="Fantasia">Fantasia</option>
				<option value="Ficção Científica">Ficção Científica</option>
				<option value="Romance">Romance</option>
				<option value="Suspense">Suspense</option>
				<option value="Terror">Terror</option>
			</select> <label for="director" class="text-white">Diretor</label> <select
				name="diretor" id="diretor"
				class="rounded-lg pl-3 h-[30px] outline-none">
				<option value="">Selecione um diretor</option>
				<%
				List<String> directors = (List<String>) request.getAttribute("directors");
				%>
				<%
				for (String directorName : directors) {
				%>
				<option value="<%=directorName%>"><%=directorName%></option>
				<%
				}
				%>
			</select> 
			 <label
				for="year" class="text-white">Ano de Lançamento</label> <input
				required type="number" name="year" id="year" min="1900" max="2024" oninput="validarAno(this)"
				value="<%=movie.getYear()%>"
				class="rounded-lg pl-3 h-[30px] outline-none"> <label
				for="synopsis" class="text-white">Sinopse</label>
			<textarea required name="synopsis" id="synopsis"
				class="rounded-lg pl-3 h-32 w-full"><%=movie.getDescription()%></textarea>
			<div>
				<img src="../.<%=movie.getPoster()%>" alt="Capa do Filme"
					class="max-w-[200px] aspect-banner rounded-lg">
			</div>
			<label for="cover" class="text-white">Capa do Filme</label> <input
				type="file" name="cover" id="cover" accept="image/*"
				class="text-white rounded-lg pl-3 h-[14%]">
			<button type="submit"
				class="bg-white text-primary rounded-lg h-[40px] font-bold hover:-translate-y-1 transition-all duration-150">Atualizar</button>
		</form>


	</main>


	<jsp:include page="footer.jsp" />
	<script>
		function validarAno(input) {
			 if (input.value > 2024) {
				input.value = 2024;
			}
		}
	</script>
</body>
</html>
