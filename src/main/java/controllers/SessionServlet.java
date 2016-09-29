package controllers;

import dto.SessionDTO;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        LocalDateTime date1 = LocalDateTime.of(2016, 9, 23, 0, 0);
        LocalDateTime[] dates = {date1, date1.plusDays(1), date1.plusDays(2)};
        List<SessionDTO> sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date1, date1.plusDays(3));
        sessionDTOs.sort((o1, o2) -> o1.getDateOfSeance().compareTo(o2.getDateOfSeance()));
        request.setAttribute("dates", dates);
        request.setAttribute("sessions", sessionDTOs);
        request.getRequestDispatcher("pages/common/session.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
