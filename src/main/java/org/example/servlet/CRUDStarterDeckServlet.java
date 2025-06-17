package org.example.servlet;

import org.example.dao.StarterDeckDAO;
import org.example.model.StarterDeck;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;            // <-- import de java.sql.Date
import java.util.List;
import java.util.Optional;

@WebServlet("/starter-decks")
public class CRUDStarterDeckServlet extends HttpServlet {
    private StarterDeckDAO dao;

    @Override
    public void init() {
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        dao = jdbi.onDemand(StarterDeckDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            List<StarterDeck> decks = dao.listAll();
            req.setAttribute("decks", decks);
            req.getRequestDispatcher("/list_starter_decks.jsp")
                    .forward(req, resp);

        } else if (action.equals("view")) {
            Optional<StarterDeck> opt = dao.findById(
                    Integer.parseInt(req.getParameter("id"))
            );
            opt.ifPresent(d -> req.setAttribute("deck", d));
            req.getRequestDispatcher("/deck_detail.jsp")
                    .forward(req, resp);

        } else if (action.equals("new")) {
            req.getRequestDispatcher("/deck_form.jsp")
                    .forward(req, resp);

        } else if (action.equals("edit")) {
            Optional<StarterDeck> opt = dao.findById(
                    Integer.parseInt(req.getParameter("id"))
            );
            opt.ifPresent(d -> req.setAttribute("deck", d));
            req.getRequestDispatcher("/deck_form.jsp")
                    .forward(req, resp);

        } else if (action.equals("delete")) {
            dao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/starter-decks?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        StarterDeck d = new StarterDeck();

        if (action.equals("create")) {
            d.setName(req.getParameter("name"));
            // Conversión LocalDate -> java.sql.Date
            LocalDate ldCreate = LocalDate.parse(req.getParameter("releaseDate"));
            d.setReleaseDate(Date.valueOf(ldCreate));
            d.setDiscontinued("on".equals(req.getParameter("discontinued")));
            d.setPrice(Float.parseFloat(req.getParameter("price")));
            d.setImage(req.getParameter("image"));
            dao.insert(d);

        } else if (action.equals("update")) {
            d.setId(Integer.parseInt(req.getParameter("id")));
            d.setName(req.getParameter("name"));
            // Conversión LocalDate -> java.sql.Date
            LocalDate ldUpdate = LocalDate.parse(req.getParameter("releaseDate"));
            d.setReleaseDate(Date.valueOf(ldUpdate));
            d.setDiscontinued("on".equals(req.getParameter("discontinued")));
            d.setPrice(Float.parseFloat(req.getParameter("price")));
            d.setImage(req.getParameter("image"));
            dao.update(d);
        }

        // Tras crear o actualizar, volvemos al listado
        resp.sendRedirect(req.getContextPath() + "/starter-decks?action=list");
    }
}
