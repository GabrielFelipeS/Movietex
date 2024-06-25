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
            <h2 class="text-3xl font-thin text-center mt-5">Filtros de busca</h2>

                <div class="w-[80%] m-auto flex flex-col gap-5">
                    <div>
                        <h3 class="text-xl font-bold my-5">Gênero</h3>
                        <ul class="flex flex-row flex-wrap gap-7">
                            <li>
                                <input type="radio" id="acao" name="genero" value="acao">
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
                        <input type="number" name="anoLancamento" id="ano" min="1900" max="2022"
                               class="w-[40%] p-2 outline-none ring-1 ring-primary rounded-lg">
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
                        </select>
                    </div>

                    <div class="flex flex-col gap-4">
                        <h3 class="font-bold">
                            Avaliação minima:
                        </h3>
                        <select name="avaliacaoMinima" id="avaliacao_minima"
                                class="w-full p-2 outline-none ring-1 ring-primary rounded-lg">
                            <option value="" disabled selected>Selecione uma Avaliação Minima</option>
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

                    <div class="flex flex-col gap-4">
                        <h3 class="font-bold">
                            Avaliação Maxima:
                        </h3>
                        <select name="avaliacaoMaxima" id="avaliacao_maxima"
                                class="w-full p-2 outline-none ring-1 ring-primary rounded-lg">
                            <option value="" disabled selected>Selecione uma Avaliação Maxima</option>
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
                    id="searchButton" name="search">
                Buscar
            </button>

        </div>
    </aside>


    <section class="w-[70%] h-full p-10 flex flex-row m-auto flex-wrap items overflow-auto" id="MoviesArea">
  
        <% List<Movie> movies = (List<Movie>) request.getAttribute("movies"); %>
        <% for(Movie movie : movies) { %>
        <div class="w-[30%] flex flex-col aspect-banner mb-5 items-center justify-center">
            <img src="<%= movie.getPoster() %>" alt="<%= movie.getTitle() %>"
                 class="w-[200px] object-cover rounded-lg">
            <p class="text-primary text-lg font-bold"><%= movie.getRatingAverage() %> / 10</p>
            <a href="./movie/<%= movie.getId() %>"
                class="bg-primary text-white p-2 rounded-lg hover:-translate-y-1 transition-all duration-150">Ver
                 detalhes</a>
            
        </div>
        <% } %>

    </section>
</main>


<jsp:include page="footer.jsp" />
<script src="js/menu.js"></script>
</body>
</html>
