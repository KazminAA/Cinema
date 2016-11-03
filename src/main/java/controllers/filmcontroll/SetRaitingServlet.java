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
 * Created by Alexandr on 29.09.2016.
 */
@WebServlet(name = "SetRaitingServlet", urlPatterns = "/setraiting")
public class SetRaitingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float value = Float.parseFloat(request.getParameter("raiting"));
        System.out.println(request.getParameter("id"));
        int key = Integer.parseInt(request.getParameter("id"));
        FilmDTO filmDTO = FilmServiceImpl.getInstance().getById(key);
        float raiting = filmDTO.getRaiting();
        if (raiting == 0) {
            raiting = value;
        } else {
            raiting = (raiting + value) / 2;
        }
        FilmServiceImpl.getInstance().updateFieldIn("rait", Float.toString(raiting), key);
        response.sendRedirect(request.getSession().getAttribute("url").toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
