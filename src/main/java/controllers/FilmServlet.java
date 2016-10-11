package controllers;

import dto.FilmDTO;
import dto.UserDTO;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandr on 26.09.2016.
 */
@WebServlet(name = "FilmServlet", urlPatterns = {"/movie"})
public class FilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("status") == null) {
            FilmDTO film = FilmServiceImpl.getInstance().getById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("film", film);
            request.getRequestDispatcher("pages/common/film.jsp").forward(request, response);
        } else {
            List<FilmDTO> films = FilmServiceImpl.getInstance().getAll();
            films.sort((o1, o2) -> (Float.compare(o2.getRaiting(), o1.getRaiting())));
            request.setAttribute("films", films);
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
            if (userDTO != null && userDTO.getRole().getName().equals("Admin")) {
                request.getRequestDispatcher("/pages/admin/filmtodel.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("/pages/common/filmsrait.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
