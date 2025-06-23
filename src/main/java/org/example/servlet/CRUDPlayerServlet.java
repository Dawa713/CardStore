package org.example.servlet;

import org.example.dao.PlayerDAO;
import org.example.model.Player;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@WebServlet("/players")
public class CRUDPlayerServlet extends HttpServlet {

    private PlayerDAO dao;

    @Override
    public void init() {
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        dao = jdbi.onDemand(PlayerDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // formulario en blanco
                req.getRequestDispatcher("/registro_player.jsp")
                        .forward(req, resp);
                break;

            case "edit":
                // cargar player y reenviar al mismo JSP
                loadPlayer(req, resp);
                break;

            case "view":
                // detalle
                loadPlayerDetail(req, resp);
                break;

            case "delete":
                // borrar y redirigir a lista
                deletePlayer(req, resp);
                break;

            case "list":
            default:
                // listar todos
                List<Player> players = dao.listarTodos();
                req.setAttribute("players", players);
                req.getRequestDispatcher("/lista_players.jsp")
                        .forward(req, resp);
                break;
        }
    }

    private void loadPlayer(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Optional<Player> opt = dao.findById(Integer.parseInt(id));
            opt.ifPresent(p -> req.setAttribute("player", p));
        }
        // form de registro/edición
        req.getRequestDispatcher("/registro_player.jsp")
                .forward(req, resp);
    }

    private void loadPlayerDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Optional<Player> opt = dao.findById(Integer.parseInt(id));
            opt.ifPresent(p -> req.setAttribute("player", p));
        }
        req.getRequestDispatcher("/detalle_player.jsp")
                .forward(req, resp);
    }

    private void deletePlayer(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            dao.eliminar(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/players");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("create".equals(action)) {
            createPlayer(req, resp);
        } else if ("update".equals(action)) {
            updatePlayer(req, resp);
        } else {
            // fallback a lista
            resp.sendRedirect(req.getContextPath() + "/players");
        }
    }

    private void createPlayer(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Player p = extractPlayerFromRequest(req, false);
        dao.insertar(p);
        resp.sendRedirect(req.getContextPath() + "/players");
    }

    private void updatePlayer(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Player p = extractPlayerFromRequest(req, true);
        dao.actualizar(p);
        resp.sendRedirect(req.getContextPath() + "/players");
    }

    private Player extractPlayerFromRequest(HttpServletRequest req, boolean includeId) {
        Player p = new Player();
        if (includeId) {
            p.setId(Integer.parseInt(req.getParameter("id")));
        }
        p.setUsername(req.getParameter("username"));
        p.setPassword(req.getParameter("password"));
        p.setEmail(req.getParameter("email"));
        p.setRole(req.getParameter("role"));
        p.setCompetitive("on".equals(req.getParameter("competitive")));
        p.setLastLogin(new Timestamp(System.currentTimeMillis()));
        return p;
    }
}
