package controllers;

import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

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
            String item = null;
            Field[] fields = new Field[0];
            switch (request.getParameter("select")) {
                case "filmprepare": {
                    item = "film";
                    fields = FilmDTO.class.getDeclaredFields();
                    disp = "pages/admin/addentity.jsp";
                    break;
                }
                case "sessionprepare": {
                    item = "session";
                    fields = SessionDTO.class.getDeclaredFields();
                    if (request.getSession().getAttribute("films") == null || request.getSession().getAttribute("halls") == null) {
                        List<FilmDTO> filmDTOs = FilmServiceImpl.getInstance().getAll();
                        filmDTOs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                        List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
                        request.getSession().setAttribute("films", filmDTOs);
                        request.getSession().setAttribute("halls", hallDTOs);
                    }
                    disp = "pages/admin/addsession.jsp";
                    break;
                }
            }
            fieldsName = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldsName[i] = fields[i].getName();
            }
            request.setAttribute("fields", fieldsName);
            request.setAttribute("entity", item);
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
