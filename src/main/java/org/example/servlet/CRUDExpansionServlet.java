// src/main/java/org/example/servlet/CRUDExpansionServlet.java
package org.example.servlet;

import org.example.dao.ExpansionDAO;
import org.example.model.Expansion;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/expansions")
public class CRUDExpansionServlet extends HttpServlet {
    private ExpansionDAO dao;

    @Override
    public void init() {
        dao = ConexionBD.obtenerJdbi().onDemand(ExpansionDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/expansion_form.jsp").forward(req, resp);
                break;
            case "edit":
                loadExpansion(req, resp, "/expansion_form.jsp");
                break;
            case "view":
                loadExpansion(req, resp, "/expansion_detail.jsp");
                break;
            case "delete":
                deleteExpansion(req, resp);
                break;
            default:
                List<Expansion> list = dao.listAll();
                req.setAttribute("expansions", list);
                req.getRequestDispatcher("/list_expansions.jsp").forward(req, resp);
                break;
        }
    }

    private void loadExpansion(HttpServletRequest req, HttpServletResponse resp, String jsp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            dao.findById(Integer.parseInt(id))
                    .ifPresent(e -> req.setAttribute("expansion", e));
        }
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    private void deleteExpansion(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        if (id != null) dao.delete(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/expansions");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        Expansion e = new Expansion();
        String id = req.getParameter("id");

        if ("create".equals(action)) {
            bindExpansion(req, e, false);
            dao.insert(e);
        } else if ("update".equals(action)) {
            bindExpansion(req, e, true);
            dao.update(e);
        }
        resp.sendRedirect(req.getContextPath() + "/expansions");
    }

    private void bindExpansion(HttpServletRequest req, Expansion e, boolean withId) {
        if (withId) e.setId(Integer.parseInt(req.getParameter("id")));
        e.setName(req.getParameter("name"));
        e.setReleaseDate(Date.valueOf(req.getParameter("releaseDate")));
        e.setDiscontinued("on".equals(req.getParameter("discontinued")));
        e.setPrice(Float.parseFloat(req.getParameter("price")));
        e.setImage(req.getParameter("image"));
    }
}
