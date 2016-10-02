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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        LocalDateTime date1 = LocalDateTime.of(2016, 9, 23, 0, 0);
        List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
        LocalDate[] dates = {date1.toLocalDate(), date1.plusDays(1).toLocalDate(), date1.plusDays(2).toLocalDate()};
        List<SessionDTO> sessionDTOs = null;
        String selectBy = request.getParameter("select");
        if (selectBy != null) {
            switch (selectBy) {
                case "film": {
                    sessionDTOs = SessionServiceImpl.getInstance().getSessionsByFK("filmID", request.getParameter("film"));
                    break;
                }
                case "hall": {
                    sessionDTOs = SessionServiceImpl.getInstance().getSessionsByFK("hallID", request.getParameter("hall"));
                    break;
                }
            }
            Set<LocalDate> datesin = new LinkedHashSet<>();
            for (SessionDTO sessionDTO : sessionDTOs) {
                if (sessionDTO.getDateOfSeance().toLocalDate().compareTo(date1.toLocalDate()) >= 0) {
                    System.out.println(sessionDTO.getDateOfSeance());
                    System.out.println(sessionDTO.getDateOfSeance().hashCode());
                    datesin.add(sessionDTO.getDateOfSeance().toLocalDate());
                }
                dates = new LocalDate[datesin.size()];
                datesin.toArray(dates);
            }
        } else {
            sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date1, date1.plusDays(3));
        }
        sessionDTOs.sort((o1, o2) -> o1.getDateOfSeance().compareTo(o2.getDateOfSeance()));
        request.setAttribute("hallDTOs", hallDTOs);
        request.setAttribute("dates", dates);
        request.setAttribute("sessions", sessionDTOs);
        request.getRequestDispatcher("pages/common/session.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
