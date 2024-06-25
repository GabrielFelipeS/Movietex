<%--
  Created by IntelliJ IDEA.
  User: Micael Hernandes
  Date: 22/06/2024
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ifsp.movietex.movie.entity.Movie" %>
<%@ page import="ifsp.movietex.rating.entity.Rating" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <jsp:include page="head.jsp" />
    <title>Filme</title>
</head>
<body>

<jsp:include page="header.jsp" />

<% Movie movie = (Movie) request.getAttribute("movie"); %>
<% List<Rating> ratings = (List<Rating>) request.getAttribute("ratings"); %>

<main class="w-[60%] h-screen m-auto flex flex-col gap-10">
    <h2 class="text-6xl font-extrabold font-serif mt-5 text-center"><%= movie.getTitle() %></h2>
    <div
            class="w-full h-[450px] flex flex-col justify-start items-center md:items-start md:flex-row md:justify-start gap-2 p-4  rounded-lg ">
        <img src=".<%= movie.getPoster() %>" alt="Cartaz do Filme X"
             class="z-10 max-w-[300px] md:w-[30%] rounded-lg">
        <div class="flex flex-col justify-start mt-1 h-full gap-3">
            <div>
                <p class="">20 de junho de 2024 | 1h 36min</p>
                <p><span class="font-bold"><a href=""><%= movie.getGenre() %></a></span></p>
                <p>Direção: <span class="font-bold"><a href=""><%= movie.getDirector() %></a></span></p>
                <p>Ano Lançamento: <span class="font-bold"><%= movie.getYear() %></span></p>
            </div>
            <div>
                <p class="font-bold ">Avaliação: <span class="text-yellow-400">  <%=movie.getRatingAverage()%>/10</span></p>
            </div>
            <div>
                <h2 class="text-2xl font-bold">Sinopse:</h2>
                <p class="text-justify"><%=movie.getDescription()%></p>
            </div>

        </div>
    </div>

    <div class="w-full h-screen">

        <h2 class="text-4xl font-serif before:h-full before:text-primary before:content-['*']">Avaliações</h2>
	<% for(Rating rating : ratings) { %>
        <div class="w-full rounded-lg mt-2 h-[200px]  flex flex-row gap-5 items-center">
            <div class="flex flex-col w-[100px] items-center">
                <div class="w-[100px] h-[100px] bg-primary rounded-full bg-cover"
                     style="background-image: url('https://img.freepik.com/fotos-gratis/pessoa-3d-vendo-um-filme-no-cinema_23-2151024901.jpg');">

                </div>
            </div>

            <div>
                <h2 class="text-xl font-serif"> <%= rating.getNota()%> / 10</h2>
                <h2 class="font-serif text-xl"><%= rating.getName()%></h2>
                <p class="text-justify"><%= rating.getComment()%></p>
            </div>
        </div>
	<% } %>
        <div class="w-full rounded-lg mt-2 h-[400px]  flex flex-col gap-5">
            <h2 class="text-4xl font-serif">Avaliar Filme</h2>
            <form action="api/rating/insert" class="w-full bg-gray-100 rounded-lg relative">
                <div class="absolute top-0 left-0 w-full h-full bg-gray-100 bg-opacity-95
    <% if (session.getAttribute("email") != null) { %>
        hidden
    <% } else { %>
        flex
    <% } %>
    rounded-lg flex-col justify-center items-center">
                    <img src="../img/figuras/ticket.png" alt="Imagem de necessario login" class="h-[80%]">
                    <h2 class="text-2xl text-black font-serif uppercase">Necessário login para poder avaliar!</h2>
                    <a href="<%=request.getContextPath()%>/login" class="p-5 bg-primary m-2 rounded-lg text-white w-[20%] text-center hover:-translate-y-1 hover:scale-105 transition-all duration-150">Logar-se</a>
                </div>

                <div class="flex flex-col gap-5 p-5 ">
                    <div class="flex flex-col gap-2">
                        <label for="nota">Nota</label>
                        <input type="number" name="nota" id="nota" min="0" max="10" class="p-2 rounded-lg" required>
                    </div>
                    <div class="flex flex-col gap-2">
                        <label for="comentario">Comentário</label>
                        <textarea name="comentario" id="comentario" cols="30" rows="10" class="p-2 rounded-lg"
                                  required></textarea>
                    </div>
                    <div class="flex flex-col gap-2">
                        <button type="submit" class="bg-primary text-white p-2 rounded-lg">Enviar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>


<jsp:include page="footer.jsp" />
<script>
    const inputNumero = document.getElementById('nota');

    inputNumero.addEventListener('input', function() {
        let valor = parseInt(this.value);
        if (isNaN(valor)) {
            this.value = '';
        } else {
            this.value = Math.max(0, Math.min(10, valor));
        }
    });
</script>
</body>
</html>
