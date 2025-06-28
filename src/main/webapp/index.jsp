<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exercício</title>
</head>
<body>
    <jsp:include page="cabecalho.html" />
    <h1>Login</h1>
    <% 
    String error = (String) request.getAttribute("error");
    %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %> 
    <form method="post" action="login">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required><br><br>
        <button type="submit">Entrar</button>
    </form>
    <jsp:include page="rodape.html" />
</body>
</html>