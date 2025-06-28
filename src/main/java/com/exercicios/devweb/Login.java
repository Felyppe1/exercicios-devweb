package com.exercicios.devweb;

import com.exercicios.devweb.env.Env;
import com.exercicios.devweb.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
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
        RequestDispatcher successReq = req.getRequestDispatcher("/index.jsp");

        successReq.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String id = rs.getString("id");
                Date dataCadastro = rs.getDate("data_criacao");
                HttpSession session = req.getSession();
                Usuario usuario = new Usuario(id, nome, email, dataCadastro);
                session.setAttribute("usuario", usuario);

                req.getRequestDispatcher("/home.jsp").forward(req, res);
            }
            else {
                req.setAttribute("error", "Email ou senha inválidos.");
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", "Erro ao realizar login: " + ex.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, res);
        }

    }
}
