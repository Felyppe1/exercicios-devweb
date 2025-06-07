<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/erro.css">
    <title>Exercício</title>
</head>
<body>
    <% 
    String erro = (String) request.getAttribute("erro");
    %>
    <p style="color:red;">
        <% if (erro == null) { %>
            Ocorreu um erro.
        <% } else { %>
            <%= erro %>
        <% } %>
    </p>
    <a href="${pageContext.request.contextPath}/metricas">Voltar</a>
</body>
</html>