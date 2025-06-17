package org.example.servlet;

import org.example.dao.CardDAO;
import org.example.dao.ExpansionDAO;
import org.example.dao.StarterDeckDAO;
import org.example.model.Card;
import org.example.model.Expansion;
import org.example.model.StarterDeck;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private CardDAO cardDao;
    private ExpansionDAO expDao;
    private StarterDeckDAO deckDao;

    @Override
    public void init() {
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        cardDao = jdbi.onDemand(CardDAO.class);
        expDao  = jdbi.onDemand(ExpansionDAO.class);
        deckDao = jdbi.onDemand(StarterDeckDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String term = "%" + req.getParameter("term") + "%";
        float max   = Float.parseFloat(req.getParameter("maxPrice"));
        String[] types = req.getParameterValues("type");

        if (types != null) {
            for (String t : types) {
                switch (t) {
                    case "cards" ->
                            req.setAttribute("cards",
                                    cardDao.searchCards(term, max));
                    case "expansions" ->
                            req.setAttribute("expansions",
                                    expDao.searchExpansions(term, max));
                    case "decks" ->
                            req.setAttribute("decks",
                                    deckDao.searchDecks(term, max));
                }
            }
        }

        req.getRequestDispatcher("/search_results.jsp")
                .forward(req, resp);
    }
}
