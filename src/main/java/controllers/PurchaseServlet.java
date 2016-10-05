package controllers;

import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 05.10.2016.
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = "/purchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketServiceImpl.getInstance().updateFieldIn("purchase", "1", Integer.parseInt(request.getParameter("ticketid")));
        response.sendRedirect(request.getSession().getAttribute("url").toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
