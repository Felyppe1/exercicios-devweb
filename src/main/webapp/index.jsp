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
    <h1>Métricas</h1>
    <div>
        <p>Você acessou este serviço <b><%= request.getAttribute("sessionCount") %></b> vez(es) nesta sessão.</p>
        <p>Você acessou este serviço <b><%= request.getAttribute("browserCount") %></b> vez(es) neste navegador.</p>
        <p>Todos os usuários acessaram este serviço <b><%= request.getAttribute("globalCount") %></b> vez(es) desde que o servidor iniciou.</p>
    </div>
    <form method="post" action="metricas">
        <label for="inicio">Início:</label>
        <input type="number" id="inicio" name="inicio" required><br><br>
        <label for="fim">Fim:</label>
        <input type="number" id="fim" name="fim" required><br><br>
        <button type="submit">Contar</button>
    </form>
    <%
        List<Integer> numeros = (List<Integer>) request.getAttribute("numeros");
        if (numeros != null) {
    %>
        <ul>
        <% for (Integer n : numeros) { %>
            <li><%= n %></li>
        <% } %>
        </ul>
    <% } %>
    <jsp:include page="rodape.html" />
</body>
</html>