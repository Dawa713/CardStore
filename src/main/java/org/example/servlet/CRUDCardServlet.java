package org.example.servlet;

import org.example.dao.CardDAO;
import org.example.model.Card;
import org.example.util.ConexionBD;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/cards")
@MultipartConfig  // permite @Part
public class CRUDCardServlet extends HttpServlet {
    private CardDAO dao;

    @Override
    public void init() {
        Jdbi jdbi = ConexionBD.obtenerJdbi();
        dao = jdbi.onDemand(CardDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || "list".equals(action)) {
            // LISTADO
            List<Card> cards = dao.listAll();
            req.setAttribute("cards", cards);
            req.getRequestDispatcher("/list_cards.jsp").forward(req, resp);
            return;
        }

        switch (action) {
            case "view" -> {
                Optional<Card> opt = dao.findById(
                        Integer.parseInt(req.getParameter("id"))
                );
                opt.ifPresent(c -> req.setAttribute("card", c));
                req.getRequestDispatcher("/card_detail.jsp").forward(req, resp);
            }
            case "new" -> {
                req.getRequestDispatcher("/card_form.jsp").forward(req, resp);
            }
            case "edit" -> {
                Optional<Card> opt = dao.findById(
                        Integer.parseInt(req.getParameter("id"))
                );
                opt.ifPresent(c -> req.setAttribute("card", c));
                req.getRequestDispatcher("/card_form.jsp").forward(req, resp);
            }
            case "delete" -> {
                try {
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                    req.getSession().setAttribute("flashSuccess", "Card deleted successfully!");
                } catch (Exception e) {
                    req.getSession().setAttribute("flashError", "Error deleting card.");
                }
                resp.sendRedirect(req.getContextPath() + "/cards?action=list");
            }
            default -> {
                // acción desconocida: devolvemos al listado
                resp.sendRedirect(req.getContextPath() + "/cards?action=list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/cards?action=list");
            return;
        }

        Card c = new Card();
        // Campos comunes
        c.setName(req.getParameter("name"));
        c.setReleaseDate(LocalDate.parse(req.getParameter("releaseDate")));
        c.setAttack(Integer.parseInt(req.getParameter("attack")));
        c.setDefense(Integer.parseInt(req.getParameter("defense")));
        c.setPrice(Float.parseFloat(req.getParameter("price")));

        // Procesar subida de imagen
        Part filePart = req.getPart("imageFile");
        if (filePart != null && filePart.getSize() > 0) {
            String filename = Paths.get(filePart.getSubmittedFileName())
                    .getFileName().toString();
            String uploadPath = req.getServletContext().getRealPath("/images");
            File uploads = new File(uploadPath);
            if (!uploads.exists()) uploads.mkdirs();
            File file = new File(uploads, filename);
            try (InputStream in = filePart.getInputStream()) {
                Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            c.setImage(filename);
        } else if ("update".equals(action)) {
            // mantener la imagen anterior si no se sube nueva
            c.setImage(req.getParameter("existingImage"));
        }

        try {
            if ("create".equals(action)) {
                dao.insert(c);
                session.setAttribute("flashSuccess", "Card created successfully!");
            } else if ("update".equals(action)) {
                c.setId(Integer.parseInt(req.getParameter("id")));
                dao.update(c);
                session.setAttribute("flashSuccess", "Card updated successfully!");
            } else {
                session.setAttribute("flashError", "Acción no reconocida.");
            }
        } catch (Exception e) {
            session.setAttribute("flashError", "Error procesando la petición.");
        }

        // Redirigir al listado tras CREATE/UPDATE
        resp.sendRedirect(req.getContextPath() + "/cards?action=list");
    }
}

