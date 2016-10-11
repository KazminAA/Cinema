package controllers.sessioncontroll;

import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import helpers.ValueHolder;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
        if ((request.getParameter("edit") != null) && (request.getParameter("edit").equals("edit"))) {
            String[] idStr = request.getParameterValues("id");
            String[] hallIdStr = request.getParameterValues("hallID");
            String[] dataStr = request.getParameterValues("seanceData");
            String[] timeStr = request.getParameterValues("seanceTime");
            String[] priceStr = request.getParameterValues("price");
            String[] filmIdStr = request.getParameterValues("filmID");
            if (idStr != null) {
                for (int i = 0; i < idStr.length; i++) {
                    SessionDTO updatingSession = new SessionDTO();
                    updatingSession.setId(Integer.parseInt(idStr[i]));
                    ValueHolder vh = new ValueHolder();
                    vh.setId(Integer.parseInt(hallIdStr[i]));
                    updatingSession.setHallAsEntity(vh);
                    updatingSession.setDateOfSeance(LocalDate.parse(dataStr[i]).atTime(LocalTime.parse(timeStr[i])));
                    try {
                        updatingSession.setPrice(new BigDecimal(priceStr[i]));
                    } catch (NumberFormatException e) {
                        return;
                    }
                    vh = new ValueHolder();
                    vh.setId(Integer.parseInt(filmIdStr[i]));
                    updatingSession.setFilmAsEntity(vh);
                    sessService.update(updatingSession);
                }
            }
            request.getRequestDispatcher("/admin/sessiontodel").forward(request, response);
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
