package controllers.ticketcontroll;

import dto.TicketDTO;
import service.api.Service;
import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "DeleteTicketServlet", urlPatterns = "/admin/deleteticket")
public class DeleteTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] toDelStr = request.getParameterValues("selected");
        Service<TicketDTO> ticketService = TicketServiceImpl.getInstance();
        for (int i = 0; i < toDelStr.length; i++) {
            ticketService.delete(Integer.parseInt(toDelStr[i]));
        }
        response.sendRedirect(request.getContextPath() + "/admin/tickettodel");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
