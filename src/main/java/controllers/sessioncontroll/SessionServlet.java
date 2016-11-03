package controllers.sessioncontroll;

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
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String selectBy = request.getParameter("select");
        List<SessionDTO> sessionDTOs, sessions;
        LocalDate beginDate, endDate, valDate;
        LocalTime time;
        if ((request.getParameter("clearDTO") != null) && (request.getParameter("clearDTO").equals("true"))) {
            request.getSession().setAttribute("sessionsDTO", null);
        }
        if (request.getSession().getAttribute("sessionsDTO") == null) {
            String beginDateStr = request.getParameter("beginDate");
            String endDateStr = request.getParameter("endDate");
            if (beginDateStr == null || beginDateStr.equals("")) {
                beginDate = LocalDate.now();
                valDate = LocalDate.now();
                time = LocalTime.now();
            } else {
                beginDate = LocalDate.parse(beginDateStr);
                valDate = LocalDate.parse(beginDateStr);
                time = LocalTime.of(0, 0);
            }
            if (endDateStr == null || endDateStr.equals("")) {
                endDate = beginDate.plusDays(3);
            } else {
                endDate = LocalDate.parse(endDateStr);
            }
            List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
            List<LocalDate> dates = new LinkedList<>();

            while (!valDate.isAfter(endDate)) {
                dates.add(valDate);
                valDate = valDate.plusDays(1);
            }
            sessionDTOs = SessionServiceImpl.getInstance().getSessionBetweenFull(beginDate.atTime(time),
                    endDate.atTime(23, 59));
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
