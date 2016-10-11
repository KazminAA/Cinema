package controllers.sessioncontroll;

import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import service.api.Service;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "EditSessionServlet", urlPatterns = "/admin/editsession")
public class EditSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service<SessionDTO> sessService = SessionServiceImpl.getInstance();
        SessionDTO[] sessionDTOs = null;
        if (request.getParameter("edit") != null && request.getParameter("edit").equals("edit")) {
            System.out.println("Yeaha");

        } else {
            String[] ids = (String[]) request.getAttribute("selected");
            if (ids != null) {
                int count = ids.length;
                if (count != 0) {
                    sessionDTOs = new SessionDTO[count];
                    for (int i = 0; i < count; i++) {
                        sessionDTOs[i] = sessService.getById(Integer.parseInt(ids[i]));
                    }
                }
                request.setAttribute("sessionsToEdit", sessionDTOs);
                List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
                request.setAttribute("halls", hallDTOs);
                List<FilmDTO> filmDTOs = FilmServiceImpl.getInstance().getAll();
                request.setAttribute("films", filmDTOs);
                request.getRequestDispatcher("/pages/admin/sessiontoedit.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
