package controllers.sessioncontroll;

import dto.SessionDTO;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
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
        LocalDate beginDate, endDate;
        String beginDateStr = request.getParameter("beginDate");
        String endDateStr = request.getParameter("endDate");
        if (beginDateStr == null || beginDateStr.equals("")) {
            beginDate = LocalDate.now();
        } else {
            beginDate = LocalDate.parse(beginDateStr);
        }
        if (endDateStr == null || endDateStr.equals("")) {
            endDate = beginDate.plusDays(5);
        } else {
            endDate = LocalDate.parse(endDateStr);
        }
        request.setAttribute("beginDate", beginDate);
        request.setAttribute("endDate", endDate);
        sessionDTOs = SessionServiceImpl.getInstance().getSessionBetweenFull(beginDate.atTime(0, 0), endDate.atTime(0, 0));
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
        request.setAttribute("sessionsToDel", sessionDTOs);
        request.getRequestDispatcher("../pages/admin/sessiontodel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
