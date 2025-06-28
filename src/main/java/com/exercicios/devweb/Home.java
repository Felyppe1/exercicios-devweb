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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class Home extends HttpServlet {
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
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            RequestDispatcher successReq = req.getRequestDispatcher("/home.jsp");
            successReq.forward(req, res);
        } else {
            req.setAttribute("error", "Você precisa estar logado para acessar esta página.");
            res.sendRedirect("index.jsp");
        }
    }
}
