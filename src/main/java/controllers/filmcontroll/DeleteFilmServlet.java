package controllers.filmcontroll;

import dto.FilmDTO;
import service.api.Service;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "DeleteFilmServlet", urlPatterns = "/admin/deletefilm")
public class DeleteFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] toDelStr = request.getParameterValues("selected");
        Service<FilmDTO> filmService = FilmServiceImpl.getInstance();
        for (int i = 0; i < toDelStr.length; i++) {
            filmService.delete(Integer.parseInt(toDelStr[i]));
        }
        response.sendRedirect(request.getContextPath() + "/movie?status=1");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
