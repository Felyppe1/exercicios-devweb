package com.exercicios.devweb;

import com.exercicios.devweb.env.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/contatos")
public class Contatos extends HttpServlet {
    private static final String URL = Env.get("DB_URL");
    private static final String USER = Env.get("DB_USER");
    private static final String PASSWORD = Env.get("DB_PASSWORD");

    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                URL, USER, PASSWORD
            );
        } catch (Exception ex) {
            throw new ServletException("Erro ao conectar ao banco de dados", ex);
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String contextPath = req.getContextPath();

        List<Map<String, String>> contatos = new ArrayList<>();

            String sql = "SELECT * FROM agenda";

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, String> contato = Map.of(
                            "name", rs.getString("name"),
                            "address", rs.getString("address"),
                            "cellphone", rs.getString("cellphone")
                        );

                        contatos.add(contato);
                    }
                }
            catch (Exception e) {
                out.print("<p>Erro ao acessar o banco de dados: " + e.getMessage() + "</p>");
            }

        // Começa HTML
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Cadastro de Contato</title>");
        out.print("</head>");
        out.print("<body>");

        out.print("<h1>Cadastrar Contato</h1>");

        out.print("<form method='post' action='" + contextPath + "/contatos'>");
        out.print("  <label for='name'>Nome:</label><br>");
        out.print("  <input type='text' id='name' name='name'><br><br>");
        out.print("  <label for='address'>Endereço:</label><br>");
        out.print("  <input type='text' id='address' name='address'><br><br>");
        out.print("  <label for='cellphone'>Celular:</label><br>");
        out.print("  <input type='text' id='cellphone' name='cellphone'><br><br>");
        out.print("  <button type='submit'>Salvar</button>");
        out.print("</form>");

        out.print("<hr>");
        out.print("<h1>Contatos já cadastrados</h1>");

        if (contatos.isEmpty()) {
            out.print("<p>Nenhum contato cadastrado ainda.</p>");
        } else {
            for (Map<String, String> contato : contatos) {
                out.print("Nome: " + contato.get("name") + ", ");
                out.print("Endereço: " + contato.get("address") + ", ");
                out.print("Celular: " + contato.get("cellphone") + "<br>");
            }
        }

        out.print("</body>");
        out.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");

        String contextPath = req.getContextPath();
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String cellphone = req.getParameter("cellphone");

        String message = null;
        boolean success = false;

        if (name == null || name.isEmpty()) {
            message = "Nome é obrigatório";
        } else if (address == null || address.isEmpty()) {
            message = "Endereço é obrigatório";
        } else if (cellphone == null || cellphone.isEmpty()) {
            message = "Celular é obrigatório";
        } else {
            String sql = """
                INSERT INTO agenda (
                    name, address, cellphone
                ) VALUES (?, ?, ?)
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, address);
                stmt.setString(3, cellphone);

                stmt.executeUpdate();
                success = true;
                message = "Conta criada com sucesso";
            } catch (Exception e) {
                message = "Erro ao criar contato: " + e.getMessage();
            }
        }

        out.print("<html>");
        out.print("<meta charset='UTF-8'>");
        out.print("<title>Resultado da criação de conta</title>");
        out.print("<body>");

        if (message != null) {
            out.printf("<p style='color:%s;'>%s</p>", success ? "green" : "red", message);
        }

        out.print("<a href='" + contextPath + "/contatos'>Voltar</a>");

        out.print("</body>");
        out.print("</html>");
    }
}
