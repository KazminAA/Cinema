package controllers.sessioncontroll;

import dto.SessionDTO;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "PrepareSessionServlet", urlPatterns = {"/admin/sessiontodel"})
public class PrepareSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String sortBy = request.getParameter("sort");
        List<SessionDTO> sessionDTOs, sessions;
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        if (beginDate == null) {
            beginDate = "";
        }
        if (endDate == null) {
            endDate = "";
        }
        if (beginDate.equals("") && endDate.equals("")) {
            sessionDTOs = SessionServiceImpl.getInstance().getAll();
            /*sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(date1, date1.plusDays(3));
            sessionDTOs.sort((o2, o1) -> o2.getDateOfSeance().compareTo(o1.getDateOfSeance()));*/
        } else if (beginDate.equals("")) {
            sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(LocalDateTime.MIN,
                    LocalDate.parse(endDate).atTime(0, 0));
        } else {
            sessionDTOs = SessionServiceImpl.getInstance().getSessionBetween(LocalDate.parse(beginDate).atTime(0, 0),
                    LocalDateTime.MAX);
        }
        sessionDTOs.sort((o2, o1) -> o2.getDateOfSeance().compareTo(o1.getDateOfSeance()));
        if (sortBy != null) {
            switch (sortBy) {
                case "hall": {
                    sessionDTOs.sort(((o1, o2) -> o2.getHall().getName().compareTo(o1.getHall().getName())));
                    break;
                }
                case "price": {
                    sessionDTOs.sort(((o1, o2) -> o2.getPrice().compareTo(o1.getPrice())));
                }
            }
        }
        System.out.println(sessionDTOs.size());
        sessionDTOs.forEach(System.out::println);
        request.setAttribute("sessions", sessionDTOs);
        request.getRequestDispatcher("../pages/admin/sessiontodel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
