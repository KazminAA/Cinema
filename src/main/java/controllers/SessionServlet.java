package controllers;

import dto.SessionDTO;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        LocalDate begin = LocalDate.of(2016, 9, 23);
        LocalDate end = LocalDate.of(2016, 9, 24);
        List<SessionDTO> sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(begin.atStartOfDay(), end.atStartOfDay());
        sessionDTOs.sort((o1, o2) -> o1.getDateOfSeance().compareTo(o2.getDateOfSeance()));
        List<String> sessions = new ArrayList<>();
        String value;
        for (SessionDTO sessionDTO : sessionDTOs) {
            value = sessionDTO.getDateOfSeance().format(DateTimeFormatter.ofPattern("H':'MM")).toString() +
                    " " + sessionDTO.getFilm().getName() + " " + sessionDTO.getHall().getName();
            sessions.add(value);
        }
        request.setAttribute("date", begin.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")));
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("pages/common/session.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
