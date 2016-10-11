package controllers.filmcontroll;

import dto.FilmDTO;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 30.09.2016.
 */
@WebServlet(name = "AddFilmServlet", urlPatterns = "/admin/addfilm")
public class AddFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int duration = 0;
        int year = 0;
        try {
            duration = Integer.parseInt(request.getParameter("durationMin"));
            year = Integer.parseInt(request.getParameter("yearOfRelease"));
            FilmDTO filmDTO = new FilmDTO(request.getParameter("name"), year,
                request.getParameter("genre"), 0, duration,
                request.getParameter("country"), request.getParameter("produser"), request.getParameter("bigPoster"),
                request.getParameter("smallPoster"), request.getParameter("description"), request.getParameter("cast"));
            FilmServiceImpl.getInstance().save(filmDTO);
            request.getRequestDispatcher("../pages/admin/select.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Неверно заданы год или длительность.");
            response.sendRedirect(request.getContextPath() + "/admin?select=filmprepare");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
