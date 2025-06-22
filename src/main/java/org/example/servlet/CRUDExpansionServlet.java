package org.example.servlet;

import org.example.dao.ExpansionDAO;
import org.example.model.Expansion;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/expansions")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CRUDExpansionServlet extends HttpServlet {
    private ExpansionDAO dao;

    @Override
    public void init() {
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        dao = jdbi.onDemand(ExpansionDAO.class);
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
                String id = req.getParameter("id");
                if (id != null) dao.delete(Integer.parseInt(id));
                resp.sendRedirect(req.getContextPath() + "/expansions");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        Expansion e = new Expansion();

        // Procesamos subida de archivo
        Part filePart = req.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName())
                .getFileName().toString();
        String imageToStore = null;
        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + fileName);
            imageToStore = fileName;
        } else {
            imageToStore = req.getParameter("existingImage");
        }

        if ("create".equals(action)) {
            bindExpansion(req, e, false);
            e.setImage(imageToStore);
            dao.insert(e);
        } else if ("update".equals(action)) {
            bindExpansion(req, e, true);
            e.setImage(imageToStore);
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
    }
}
