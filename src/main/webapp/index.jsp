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
    <%@ include file="head.jsp" %>
    <title>Title</title>
</head>
<body>

<%@ include file="header.jsp" %>

<main class="w-[60%] mx-auto h-[100px] rounded-lg">
    <div class="swiper mySwiper">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <img src="https://uploads.jovemnerd.com.br/wp-content/uploads/2024/03/divertida_mente_2_novas_emocoes_cartazes__32h203w.jpg?ims=1210x544/filters:quality(75)"
                     class="w-full" />
            </div>
            <div class="swiper-slide">
                <img src="https://assets.papelpop.com/wp-content/uploads/2024/04/bbaf-scaled.jpg"
                     class="w-full bg-cover" />
            </div>

        </div>
        <div class="swiper-pagination"></div>
    </div>
    <!-- Filmes area -->
    <div class="w-full flex flex-col mt-10">
        <div class=" flex top-full flex-col z-100 w-full h-auto max-h-[80vh] overflow-auto gap-5 mt-2">
            <!-- Exemplo de card -->
            <div
                    class="w-full h-[450px] flex flex-col justify-center items-center md:items-start md:flex-row md:justify-start gap-2 p-4 bg-gray-100 rounded-lg ">
                <img src="img/capas/divertida_mente.webp" alt="Cartaz do Filme X"
                     class="z-10 max-w-[300px] md:w-[30%] rounded-lg">
                <div class="flex flex-col justify-center h-full gap-3">
                    <div>
                        <h2 class="text-2xl font-bold">Divertida Mente 2</h2>
                        <p class="">20 de junho de 2024 | 1h 36min</p>
                        <p><span class="font-bold"><a href="">Aventura</a> , <a href="">Ação</a>, <a
                                href="">Comédia</a></span></p>
                        <p>Direção: <span class="font-bold"><a href="">Kelsey Mann</a></span></p>
                        <p>Ano Lançamento: <span class="font-bold">2024</span></p>
                    </div>
                    <div>
                        <p class="font-bold ">Avaliação: <span class="text-yellow-400"><i
                                class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i
                                class="fa-solid fa-star"></i></span></p>
                    </div>
                    <div>
                        <p class="text-justify"><span class="font-bold">Sinopse:</span> Lorem ipsum dolor sit amet
                            consectetur adipisicing elit. Eveniet, fugit ad? Deserunt accusamus rerum consectetur et
                            omnis, nihil pariatur possimus reiciendis, libero vero, alias odio id eaque quasi
                            voluptas accusantium!</p>
                    </div>
                    <div>
                        <button class="w-[100px] h-[50px] mt-3 bg-primary text-secondary rounded-lg p-2">Saiba
                            Mais</button>
                    </div>
                </div>
            </div>
            <!-- Fim exemplo de card -->

        </div>
    </div>
</main>


<jsp:include page="footer.jsp" />
</body>
</html>
