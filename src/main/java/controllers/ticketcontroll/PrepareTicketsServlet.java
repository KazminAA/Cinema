package controllers.ticketcontroll;

import dto.TicketDTO;
import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "PrepareTicketsServlet", urlPatterns = {"/admin/tickettodel"})
public class PrepareTicketsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String sortBy = request.getParameter("sort");
        List<TicketDTO> ticketDTOs;
        ticketDTOs = TicketServiceImpl.getInstance().getAllFull();
        ticketDTOs.sort((o2, o1) -> o2.getTimecreate().compareTo(o1.getTimecreate()));
        if (sortBy != null) {
            switch (sortBy) {
                case "session": {
                    ticketDTOs.sort(((o1, o2) -> o2.getSession().getDateOfSeance().compareTo(o1.getSession().getDateOfSeance()) +
                            (o2.getSession().getHall().getId() - o1.getSession().getHall().getId())));
                    break;
                }
            }
        }
        request.setAttribute("ticketsToDel", ticketDTOs);
        request.getRequestDispatcher("../pages/admin/tickettodel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
