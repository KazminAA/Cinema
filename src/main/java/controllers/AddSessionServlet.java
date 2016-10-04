package controllers;

import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr on 30.09.2016.
 */
@WebServlet(name = "AddSessionServlet", urlPatterns = "/admin/addsession")
public class AddSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BigDecimal price;
        try {
            price = new BigDecimal(request.getParameter("price"));
            FilmDTO filmDTO = null;
            HallDTO hallDTO = null;
            List<FilmDTO> filmDTOs;
            List<HallDTO> hallDTOs;
            if ((filmDTOs = (List<FilmDTO>) request.getSession().getAttribute("films")) == null) {
                filmDTOs = FilmServiceImpl.getInstance().getAll();
            }
            if ((hallDTOs = (List<HallDTO>) request.getSession().getAttribute("halls")) == null) {
                hallDTOs = HallServiceImpl.getInstance().getAll();
            }
            for (FilmDTO film : filmDTOs) {
                if (film.getId() == Integer.parseInt(request.getParameter("filmID"))) {
                    filmDTO = film;
                    break;
                }
            }
            for (HallDTO hall : hallDTOs) {
                if (hall.getId() == Integer.parseInt(request.getParameter("hallID"))) ;
                hallDTO = hall;
                break;
            }
            SessionDTO sessionDTO = new SessionDTO(filmDTO, LocalDateTime.parse(request.getParameter("dateOfSeance")),
                    hallDTO, price);
            SessionServiceImpl.getInstance().save(sessionDTO);
            request.getRequestDispatcher("../pages/admin/select.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Неверно задана цена.");
            response.sendRedirect(request.getContextPath() + "/admin?select=sessionprepare");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
