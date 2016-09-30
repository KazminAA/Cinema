package controllers;

import dto.FilmDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Alexandr on 30.09.2016.
 */
@WebServlet(name = "AdminSelectServlet", urlPatterns = "/admin")
public class AdminSelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String disp = null;
        if (request.getParameter("select") != null) {
            String[] fieldsName;
            String item;
            switch (request.getParameter("select")) {
                case "filmprepare": {
                    item = "film";
                    Field[] fields = FilmDTO.class.getDeclaredFields();
                    fieldsName = new String[fields.length];
                    for (int i = 0; i < fields.length; i++) {
                        fieldsName[i] = fields[i].getName();
                    }
                    request.setAttribute("fields", fieldsName);
                    request.setAttribute("entity", item);
                    break;
                }
                case "addfilm": {

                }
            }
            disp = "pages/admin/addentity.jsp";
        } else {
            disp = "pages/admin/select.jsp";
        }
        request.getRequestDispatcher(disp).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
