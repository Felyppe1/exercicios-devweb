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
    <h1>Outra página</h1>

    <%
        Map<String, Object> usuario = (Map<String, Object>) session.getAttribute("usuario");
    %>
    <p>Olá, <strong><%= usuario.get("nome") %></strong>!</p>
    <p>Email: <%= usuario.get("email") %></p>
    <jsp:include page="rodape.html" />
</body>
</html>