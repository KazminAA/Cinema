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
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        LocalDate date1 = LocalDate.of(2016, 9, 23);
        LocalDate date2 = date1.plusDays(1);
        LocalDate date3 = date1.plusDays(2);
        Comparator<SessionDTO> comp = new Comparator<SessionDTO>() {
            @Override
            public int compare(SessionDTO o1, SessionDTO o2) {
                return o1.getDateOfSeance().compareTo(o2.getDateOfSeance());
            }
        };
        List<SessionDTO> sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date1.atStartOfDay(), date2.atStartOfDay());
        sessionDTOs.sort(comp);
        request.setAttribute("date1", date1.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")) + " "
                + date1.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru")));
        request.setAttribute("sessions1", sessionDTOs);
        sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date2.atStartOfDay(), date3.atStartOfDay());
        sessionDTOs.sort(comp);
        request.setAttribute("date2", date2.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")) + " "
                + date2.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru")));
        request.setAttribute("sessions2", sessionDTOs);
        sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date3.atStartOfDay(), date3.plusDays(1).atStartOfDay());
        sessionDTOs.sort(comp);
        request.setAttribute("date3", date3.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")) + " "
                + date3.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru")));
        request.setAttribute("sessions3", sessionDTOs);
        request.getRequestDispatcher("pages/common/session.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
