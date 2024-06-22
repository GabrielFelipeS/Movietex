<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="w-full flex flex-col items-center justify-center h-auto bg-white">
  <div class="flex flex-row justify-center items-center gap-5 w-[90%] md:w-[60%] h-[100px] ">
    <div>
      <h1 class="text-4xl hidden md:flex text-primary font-extrabold">MOVIE <span
              class="text-secondary">TEX</span> </h1>
    </div>
    <div class="w-full md:w-[90%] h-[50%] flex flex-row ring-1 ring-black rounded-lg relative">
      <div class="w-full flex justify-center items-center">
        <input type="text" placeholder="Procurar por filme, uma série, uma personalidade..."
               class="w-[90%] bg-transparent pl-3 text-black placeholder:text-black outline-none z-1">
      </div>
      <div
              class="flex justify-center items-center p-1 w-[10%] h-[100%] bg-secondary hover:bg-primary cursor-pointer hover:text-white rounded-e-lg">
        <i class="fa-solid fa-magnifying-glass"></i>
      </div>
      <div
              class="absolute hidden top-full left-0 flex-col z-100 w-full h-auto max-h-[80vh] overflow-auto gap-5 mt-2">
        <div
                class="w-full h-auto flex flex-col justify-center items-center md:items-start md:flex-row md:justify-start gap-2 p-4 bg-gray-100 rounded-lg">
          <img src="img/capas/divertida_mente.webp" alt="Cartaz do Filme X"
               class="z-10 max-w-[300px] md:w-[30%] rounded-lg">
          <div class="flex flex-col">
            <h2 class="text-2xl font-bold">Divertida Mente 2</h2>
            <p class="">20 de junho de 2024 | 1h 36min</p>
            <p><span class="font-bold"><a href="">Aventura</a> , <a href="">Ação</a>, <a
                    href="">Comédia</a></span></p>
            <p>Direção: <span class="font-bold"><a href="">Kelsey Mann</a></span></p>
            <p>Ano Lançamento: <span class="font-bold">2024</span></p>
          </div>
        </div>

        <div class="w-full h-[100px] rounded-lg bg-gray-100 flex flex-row items-center justify-around">
          <h2 class="text-xl p-3 font-bold">Nenhum resultado para: <span class="text-primary">Teste</span>
          </h2>
          <img src="img/figuras/pipoca_chao.png" alt="" class="h-[100%]">
        </div>

      </div>
    </div>
  </div>
  <div class="w-full h-auto flex flex-col">
    <div class="w-[60%] flex flex-row mx-auto">
      <nav class="flex flex-row justify-between w-full m-auto">
        <ul class="flex flex-row">
          <li class="p-3 bg-primary rounded-t-lg text-white font-bold"><a href="">Início</a></li>
          <li
                  class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
            <a href="">Filmes</a>
          </li>
          <!-- <li
              class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
              <a href="">Séries</a>
          </li> -->
        </ul>
        <ul class="flex flex-row">
          <li
                  class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
            <a href="">Login</a>
          </li>
          <li
                  class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
            <a href="">Cadastro</a>
          </li>
          <li
                  class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
            <a href="">Painel</a>
          </li>
          <li
                  class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
            <a href="">Sair</a>
          </li>
        </ul>
      </nav>
    </div>
    <div class="h-[40%] w-full mx-auto bg-primary">
      <ul class="flex w-[60%] gap-1 mx-auto flex-row justify-start items-center text-white">
        <li class="p-1">Lançamentos</li>
        <li class="p-1">Lançamentos</li>
        <li class="p-1">Lançamentos</li>
        <li class="p-1">Lançamentos</li>
        <li class="p-1">Lançamentos</li>
        <li class="p-1">Lançamentos</li>
      </ul>
    </div>
  </div>
</header>