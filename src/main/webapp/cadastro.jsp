<%--
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
    <title>cadastro de filme</title>
</head>
<body>

<jsp:include page="header.jsp" />

<main class="w-full flex flex-col justify-center items-center">
    <div>
        <h2 class="text-4xl font-bold text-primary p-5">Cadastrar Filme</h2>
    </div>
    <form action="api/movie/insert" method="POST" class="w-[550px] justify-around rounded-lg flex flex-col bg-primary p-4 gap-3" enctype="multipart/form-data">
        <label for="title" class="text-white">Nome do filme</label>
        <input required type="text" name="title" id="title" class="rounded-lg pl-3 h-[30px] outline-none">
        <label for="duration" class="text-white">Duração</label>
        <input required type="text" name="duration" id="duration" class="rounded-lg pl-3 h-[30px] outline-none">
        <label for="genre"class="text-white">Gênero</label>
        <select required name="genre" id="genre" class="rounded-lg pl-3 h-[30px] outline-none">
            <option value="">Selecione um gênero</option>
            <option value="Ação">Ação</option>
            <option value="Aventura">Aventura</option>
            <option value="Comédia">Comédia</option>
            <option value="Drama">Drama</option>
            <option value="Fantasia">Fantasia</option>
            <option value="Ficção Científica">Ficção Científica</option>
            <option value="Romance">Romance</option>
            <option value="Suspense">Suspense</option>
            <option value="Terror">Terror</option>
        </select>
        <label for="director" class="text-white">Diretor</label> <select
            name="diretor" id="diretor"
            class="rounded-lg pl-3 h-[30px] outline-none">
        <option value="" disabled selected>Selecione um diretor</option>    
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
        <label for="year" class="text-white">Ano de Lançamento</label>
        <input required type="text" name="year" id="year" class="rounded-lg pl-3 h-[30px] outline-none">
        <label for="description" class="text-white">Sinopse</label>
        <textarea required name="description" id="description" class="rounded-lg pl-3 h-32 w-full"></textarea>
        <label for="file" class="text-white">Capa do Filme</label>
        <input required type="file" name="file" id="file" accept="image/*"
               class="text-white rounded-lg pl-3 h-[30px] outline-none">
        <button type="submit"
                class="bg-white text-primary rounded-lg h-[40px] font-bold hover:-translate-y-1 transition-all duration-150">Cadastrar</button>
    </form>
</main>


<jsp:include page="footer.jsp" />
</body>
</html>
