package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "ExitServlet", urlPatterns = "/exit")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> names = request.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            request.getSession().setAttribute(names.nextElement(), null);
        }
        names = request.getAttributeNames();
        while (names.hasMoreElements()) {
            request.setAttribute(names.nextElement(), null);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
