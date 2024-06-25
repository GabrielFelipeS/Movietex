<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<header
	class="w-full flex flex-col items-center justify-center h-auto bg-white">
	<div
		class="flex flex-row justify-center items-center gap-5 w-[90%] md:w-[60%] h-[100px] ">
		<div>
			<h1 class="text-4xl hidden md:flex text-primary font-extrabold">
				MOVIE <span class="text-secondary">TEX</span>
			</h1>
		</div>
		<div
			class="w-full md:w-[90%] h-[50%] flex flex-row ring-1 ring-black rounded-lg relative">
			<div class="w-full flex justify-center items-center">
				<input type="text" placeholder="Procurar por titulo, genero, ano..."
					class="w-[90%] bg-transparent pl-3 text-black placeholder:text-black outline-none z-1"
					id="search">
			</div>
			<div
				class="flex justify-center items-center p-1 w-[10%] h-[100%] bg-secondary hover:bg-primary cursor-pointer hover:text-white rounded-e-lg">
				<i class="fa-solid fa-magnifying-glass"></i>
			</div>
			<div
				class="absolute flex top-full left-0 flex-col z-100 w-full h-auto max-h-[80vh] overflow-auto gap-5 mt-2"
				style="z-index: 99999" id="resultArea"></div>
		</div>
	</div>
	<div class="w-full h-auto flex flex-col">
		<div class="w-[60%] flex flex-row mx-auto">
			<nav class="flex flex-row justify-between w-full m-auto">
				<ul class="flex flex-row">
					<li
						class="p-3 rounded-t-lg text-primary font-bold  hover:bg-gray-100  duration-150 ease-in-out"><a
						href="./">Início</a></li>
					<li
						class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100  duration-150 ease-in-out">
						<a href="./movies">Filmes</a>
					</li>
					<!-- <li
              class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100 hover:-translate-y-1 transition-all duration-150 ease-in-out">
              <a href="">Séries</a>
          </li> -->
				</ul>
				<ul class="flex flex-row">

					<%
					if (session.getAttribute("email") == null) {
					%>
					<li
						class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100  duration-150 ease-in-out">
						<a href="login.jsp">Login</a>
					</li>
					<li
						class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100  duration-150 ease-in-out">
						<a href="registro.jsp">Registre-se</a>
					</li>
					<%}%>

					<%
					if (session.getAttribute("isAdmin") != null && session.getAttribute("isAdmin").equals("admin")) {
					%>

					<li
						class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100  duration-150 ease-in-out">
						<a href="painel.jsp">Painel</a>
					</li>
					
						<%}%>

					<%
					if (session.getAttribute("email") != null) {
					%>
					
					<li
						class="p-3 text-primary font-bold rounded-t-lg hover:bg-gray-100  duration-150 ease-in-out">
						<a href="logout">Sair</a>
					</li>
					
					<%}%>
				</ul>
			</nav>
		</div>
		<div class="h-[40%] w-full mx-auto bg-primary">
			<ul
				class="flex w-[60%] gap-1 mx-auto flex-row justify-start items-center text-white">
				<li class="p-1"></li>
				<li class="p-1"></li>
				<li class="p-1"></li>
				<li class="p-1"></li>
				<li class="p-1"></li>
				<li class="p-1"></li>
			</ul>
		</div>
	</div>
</header>