package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "DoChoeseServlet", urlPatterns = "/admin/dochoese")
public class DoChoeseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selected = request.getParameterValues("selected");
        request.setAttribute("selected", selected);
        String choese = request.getParameter("act");
        switch (choese) {
            case "Delete Session": {
                request.getRequestDispatcher("/admin/delsession").forward(request, response);
                break;
            }
            case "Edit Session": {
                request.getRequestDispatcher("/admin/editsession").forward(request, response);
                break;
            }
            case "Delete Film": {
                request.getRequestDispatcher("/admin/deletefilm").forward(request, response);
                break;
            }
            case "Edit Film": {
                request.getRequestDispatcher("/admin/editfilm").forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
