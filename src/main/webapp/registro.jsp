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
    <title>Registro Usuario</title>
</head>
<body>

<jsp:include page="header.jsp" />

<main class="w-full h-[80vh] flex flex-col justify-center items-center">
    <div class="hidden flex-row items-center mb-5 justify-center w-[350px] h-[50px] bg-secondary rounded-lg">
        <h2 class="text-white">Erro ao Logar</h2>
    </div>
    <div class="w-[80%] h-[450px] bg-gray rounded-lg flex flex-col justify-evenly items-center">
        <h2 class="text-2xl text-primary font-bold">Registre-se</h2>
        <form action="UserRegister" method="POST" class="flex flex-col w-[40%] h-[60%] justify-center gap-5">
            <input type="text" name= "name" placeholder="Nome" class="w-full h-[40px] bg-gray-100 rounded-lg p-2 outline-none"
                   required>
            <input type="text" name="email" placeholder="E-mail" class="w-full h-[40px] bg-gray-100 rounded-lg p-2 outline-none"
                   required>
            <input type="password" name="password" placeholder="Senha"
                   class="w-full h-[40px] bg-gray-100 rounded-lg p-2 outline-none" required>
            <button
                    class="w-full h-[40px] bg-secondary text-white rounded-lg hover:-translate-y-1 hover:bg-primary transition-all duration-150">Registrar</button>
        </form>
        <a href=""
           class="text-xl font-bold text-white animate-bounce bg-secondary px-5 py-2 rounded-lg hover:animate-none transition-all duration-150">Logar-se</a>
    </div>
</main>


<jsp:include page="footer.jsp" />
</body>
</html>
