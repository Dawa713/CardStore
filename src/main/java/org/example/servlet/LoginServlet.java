package org.example.servlet;

import org.example.dao.PlayerDAO;
import org.example.model.Player;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet encargado de mostrar el formulario de login (GET)
 * y de procesar la autenticación (POST).
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * GET /login → muestra login.jsp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    /**
     * POST /login → procesa usuario/clave, guarda en sesión o devuelve error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Leemos credenciales del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2) Consultamos la BD vía Jdbi/PlayerDAO
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        PlayerDAO dao = jdbi.onDemand(PlayerDAO.class);
        Optional<Player> optPlayer = dao.authenticate(username, password);

        if (optPlayer.isPresent()) {
            // 3a) Login OK → guardamos Player en sesión y redirigimos a Home.jsp
            HttpSession session = req.getSession(true);
            session.setAttribute("user", optPlayer.get());
            resp.sendRedirect(req.getContextPath() + "/Home.jsp");
        } else {
            // 3b) Login KO → devolvemos a login.jsp con mensaje de error
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/login.jsp")
                    .forward(req, resp);
        }
    }
}
