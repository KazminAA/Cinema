package controllers;

import dto.HallDTO;
import dto.SessionDTO;
import service.impl.HallServiceImpl;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String selectBy = request.getParameter("select");
        List<SessionDTO> sessionDTOs, sessions;
        if (request.getSession().getAttribute("sessionsDTO") == null) {
            LocalDateTime date1 = LocalDateTime.of(2016, 9, 23, 0, 0);
            List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
            LocalDate[] dates = {date1.toLocalDate(), date1.plusDays(1).toLocalDate(), date1.plusDays(2).toLocalDate()};
            sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date1, date1.plusDays(3));
            sessionDTOs.sort((o2, o1) -> o2.getDateOfSeance().compareTo(o1.getDateOfSeance()));
            request.getSession().setAttribute("hallDTOs", hallDTOs);
            request.getSession().setAttribute("dates", dates);
            request.getSession().setAttribute("sessionsDTO", sessionDTOs);
        } else {
            sessionDTOs = (List<SessionDTO>) request.getSession().getAttribute("sessionsDTO");
        }
        if (selectBy != null) {
            sessions = new LinkedList<>();
            for (SessionDTO sessionDTO : sessionDTOs) {
                switch (selectBy) {
                    case "film": {
                        if (sessionDTO.getFilm().getId() == Integer.parseInt(request.getParameter("film"))) {
                            sessions.add(sessionDTO);
                        }
                        break;
                    }
                    case "hall": {
                        if (sessionDTO.getHall().getId() == Integer.parseInt(request.getParameter("hall"))) {
                            sessions.add(sessionDTO);
                        }
                        break;
                    }
                }
            }
        } else {
            sessions = sessionDTOs;
        }
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("pages/common/session.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
