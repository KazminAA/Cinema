package controllers;

import dto.TicketDTO;
import dto.UserDTO;
import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandr on 05.10.2016.
 */
@WebServlet(name = "UserAreaServlet", urlPatterns = "/personalarea")
public class UserAreaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TicketDTO> tickets = TicketServiceImpl.getInstance().getUserTickets(((UserDTO) request.
                getSession().getAttribute("user")).getId());
        tickets.sort((o1, o2) -> o1.getSession().getDateOfSeance().compareTo(o2.getSession().getDateOfSeance()));
        for (TicketDTO ticket : tickets) {
            System.out.println(ticket.getSession().getDateOfSeance() + " " + ticket.isPurchase());
        }
        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/pages/common/personalarea.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
