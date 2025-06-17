package org.example.filter;

import org.example.model.Player;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    // Rutas públicas
    private static final Set<String> openPaths = Set.of(
            "/login", "/logout", "/css/", "/images/"
    );

    // Acciones CRUD que bloqueamos para role=user
    private static final Set<String> blockedActions = Set.of(
            "new","create","edit","update","delete"
    );

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest) rq;
        HttpServletResponse resp = (HttpServletResponse) rs;
        String path   = req.getServletPath();
        String action = req.getParameter("action");

        // 1) Rutas completamente públicas
        for (String op : openPaths) {
            if (path.startsWith(op)) {
                chain.doFilter(rq, rs);
                return;
            }
        }

        // 2) Usuario en sesión
        HttpSession session = req.getSession(false);
        Player user = session != null
                ? (Player) session.getAttribute("user")
                : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 3) Si es role=user, bloqueamos:
        if ("user".equals(user.getRole())) {

            // 3a) Bloquear view de players
            if (path.equals("/players") && "view".equals(action)) {
                session.setAttribute("flashError", "Lo siento, no tienes permiso para eso.");
                resp.sendRedirect(req.getContextPath() + "/players?action=list");
                return;
            }

            // 3b) Bloquear el resto de acciones CRUD genéricas
            if (action != null && blockedActions.contains(action)) {
                session.setAttribute("flashError", "Lo siento, no tienes permiso para eso.");
                // redirige al listado de la entidad:
                resp.sendRedirect(req.getContextPath() + path + "?action=list");
                return;
            }
        }

        // 4) Si llega aquí, está autorizado
        chain.doFilter(rq, rs);
    }
}
