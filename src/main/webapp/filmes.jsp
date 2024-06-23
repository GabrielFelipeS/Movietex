<%@ page import="ifsp.movietex.movie.entity.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Micael Hernandes
  Date: 22/06/2024
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="head.jsp" />
    <title>Filmes</title>
</head>
<body>

<jsp:include page="header.jsp" />

<main class="w-full h-[80vh] flex flex-row">
    <aside class="w-[30%] h-full  flex flex-col justify-center items-center">
        <div
                class="w-[80%] h-[90%] bg-white rounded-lg flex flex-col  shadow-secondary drop-shadow-xl">
            <h2 class="text-3xl font-thin text-center mt-5 font-bold">Filtros de busca</h2>

            <div class="w-[80%] m-auto flex flex-col gap-5">
                <div>
                    <h3 class="text-xl font-bold my-5">Gênero</h3>
                    <ul class="flex flex-row flex-wrap gap-7">
                        <li>
                            <input type="radio" id="acao" name="genero" value="acao" checked>
                            <label for="acao" class="font-bold">Ação</label>
                        </li>
                        <li>
                            <input type="radio" id="aventura" name="genero" value="aventura">
                            <label for="aventura" class="font-bold">Aventura</label>
                        </li>
                        <li>
                            <input type="radio" id="comedia" name="genero" value="comedia">
                            <label for="comedia" class="font-bold">Comédia</label>
                        </li>
                        <li>
                            <input type="radio" id="drama" name="genero" value="drama">
                            <label for="drama" class="font-bold">Drama</label>
                        </li>
                        <li>
                            <input type="radio" id="ficcao" name="genero" value="ficcao">
                            <label for="ficcao" class="font-bold">Ficção Científica</label>
                        </li>
                        <li>
                            <input type="radio" id="terror" name="genero" value="terror">
                            <label for="terror" class="font-bold">Terror</label>
                        </li>
                    </ul>
                </div>
                <div class="flex flex-row justify-start items-center gap-4">
                    <h3 class="font-bold">
                        Ano de Lançamento:
                    </h3>
                    <input type="number" name="ano" id="ano" min="1900" max="2022"
                           class="w-[40%] p-2 outline-none ring-1 ring-primary rounded-lg" value="2024">
                </div>

                <div class="flex flex-col gap-2">
                    <h3 class="text-xl font-bold my-1">Diretor</h3>
                    <select name="diretor" id="diretor"
                            class="w-full p-2 outline-none ring-1 ring-primary rounded-lg">
                        <option value="">Selecione um diretor</option>
                                <% List<String> directors = (List<String>) request.getAttribute("directors"); %>
       							<% for(String directorName : directors) { %>
                        			 <option value="<%= directorName %>"><%= directorName %></option>
                               <% } %>
                        <option value="Steven Spielberg">Steven Spielberg</option>
                        <option value="Christopher Nolan">Christopher Nolan</option>
                        <option value="Quentin Tarantino">Quentin Tarantino</option>
                        <option value="Martin Scorsese">Martin Scorsese</option>
                        <option value="David Fincher">David Fincher</option>
                    </select>
                </div>

                <div class="flex flex-col gap-4">
                    <h3 class="font-bold">
                        Avaliação:
                    </h3>
                    <select name="avaliacao" id="avaliacao"
                            class="w-full p-2 outline-none ring-1 ring-primary rounded-lg">
                        <option value="" disabled selected>Selecione uma avaliação</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
            </div>

            <button
                    class="bg-primary w-[80%] m-auto p-3 text-white rounded-lg hover:-translate-y-1 transition-all duration-150"
                    id="searchButton">
                Buscar
            </button>

        </div>
    </aside>


    <section class="w-[70%] h-full p-10 flex flex-row flex-wrap items overflow-auto">
  
        <% List<Movie> movies = (List<Movie>) request.getAttribute("movies"); %>
        <% for(Movie movie : movies) { %>
        <div class="w-[30%] h-[50%] flex flex-col items-center justify-center">
            <img src="img/filmes/<%= movie.getPoster() %>" alt="<%= movie.getTitle() %>"
                 class="w-[80%] h-[80%] object-cover rounded-lg">
            <p class="text-primary text-2xl font-bold"><%= movie.getTitle() %></p>
            <p class="text-primary text-lg font-bold"><%= movie.getGenre() %></p>
            <p class="text-primary text-lg font-bold"><%= movie.getYear() %></p>
            <p class="text-primary text-lg font-bold"><%= movie.getDirector() %></p>
            <p class="text-primary text-lg font-bold"><%= movie.getRatingAverage() %></p>
        </div>
        <% } %>
        <div id="404" class="w-full h-full hidden flex-col items-center justify-center">
            <img src="img/figuras/404.png" alt="Erro na pesquisa" class="w-auto">
            <p class="text-primary text-2xl font-bold">Nenhum resultado para os filtros informados!</p>
        </div>
    </section>
</main>


<jsp:include page="footer.jsp" />
</body>
</html>
