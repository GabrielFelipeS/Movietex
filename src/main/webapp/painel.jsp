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
    <title>Painel ADM</title>
</head>
<body>

<jsp:include page="header.jsp" />

<main class="w-full h-[80vh] flex flex-row">
    <aside class="w-[20%] h-full bg-gray-100 flex flex-col gap-10">
        <h2 class="text-center text-2xl text-primary mt-10"> Painel Administrativo </h2>
        <ul class="flex flex-col gap-2 p-2">
            <li
                    class="p-2 bg-primary text-white font-bold rounded-lg hover:-translate-y-1 transition-all duration-150">
                <a href="">Filmes</a>
            </li>
            <!-- <li class="p-2 bg-primary text-white font-bold rounded-lg"> <a href="">Séries</a> </li>
            <li class="p-2 bg-primary text-white font-bold rounded-lg"> <a href="">Usuários</a> </li>
            <li class="p-2 bg-primary text-white font-bold rounded-lg"> <a href="">Configurações</a> </li> -->
        </ul>
    </aside>

    <section class="w-[80%] h-full flex flex-col pl-3 gap-5">
        <h2 class="text-start text-4xl text-primary mt-5"> Filmes </h2>

        <button
                class="p-2 bg-green-600 w-[150px] rounded-lg text-white mt-2 hover:-translate-y-1 transition-all duration-150">
            Cadastrar filmes</button>


        <div class="w-full max-h-[80vh] overflow-auto flex flex-col gap-4">
            <div
                    class="w-[90%] h-[150px] bg-primary text-white rounded-lg flex flex-row gap-10 justify-between items-center">
                <div class="flex flex-row gap-5 h-full items-center ml-2">
                    <img src="img/capas/divertida_mente.webp" alt="Cartaz do Filme X" class="h-[80%] rounded-lg">
                    <div>
                        <h2 class="text-2xl">Filme X</h2>
                        <p>20 de junho de 2024 | 1h 36min</p>
                        <p><span class="font-bold"><a href="">Aventura</a> , <a href="">Ação</a>, <a
                                href="">Comédia</a></span></p>
                        <p>Direção: <span class="font-bold"><a href="">Kelsey Mann</a></span></p>
                        <p>Ano Lançamento: <span class="font-bold">2024</span></p>
                    </div>
                </div>
                <div class="flex items-center p-5 gap-5">
                    <a href="" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-pen-to-square text-2xl text-white"></i></a>
                    <a href="" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-trash-can text-2xl text-white"></i></a>
                </div>
            </div>
        </div>
    </section>
</main>


<jsp:include page="footer.jsp" />
</body>
</html>
