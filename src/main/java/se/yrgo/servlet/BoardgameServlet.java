package se.yrgo.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * When running servlet, go to http://localhost:32772/boardgame-club-administration
 */
@WebServlet(name = "BoardgameServlet", urlPatterns = {"/boardgames"})
public class BoardgameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Welcome, Boardgamers!</h1>");
    }
}
