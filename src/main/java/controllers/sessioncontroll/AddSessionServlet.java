package controllers.sessioncontroll;

import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr on 30.09.2016.
 */
@WebServlet(name = "AddSessionServlet", urlPatterns = "/admin/addsession")
public class AddSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        LocalDate beginDate = null, endDate = null;
        BigDecimal price;
        try {
            if (request.getParameter("beginDate").equals("")) {
                fieldErr(request, response, "Не заполнена начальная дата.");
            } else {
                beginDate = LocalDate.parse(request.getParameter("beginDate"));
            }
            if (request.getParameter("endDate").equals("")) {
                fieldErr(request, response, "Не заполнена конечная дата.");
            } else {
                endDate = LocalDate.parse(request.getParameter("endDate"));
            }
            String[] times = request.getParameterValues("beginTime");
            List<LocalDateTime> seansOnDayList = new ArrayList<>();
            for (int i = 0; i < times.length; i++) {
                if (!times[i].equals("")) {
                    seansOnDayList.add(beginDate.atTime(LocalTime.parse(times[i])));
                }
            }
            LocalDateTime[] seansOnDay = new LocalDateTime[0];
            seansOnDay = seansOnDayList.toArray(seansOnDay);
            int len = seansOnDay.length - 1;
            while (seansOnDay[len].isBefore(seansOnDay[0])) {
                seansOnDay[len] = seansOnDay[len].plusDays(1);
                len--;
            }
            price = new BigDecimal(request.getParameter("priceChk"));
            FilmDTO filmDTO = (FilmDTO) request.getSession().getAttribute("film");
            request.getSession().setAttribute("film", null);
            HallDTO hallDTO = (HallDTO) request.getSession().getAttribute("hall");
            request.getSession().setAttribute("hall", null);
            SessionDTO sessionDTO;
            do {
                for (int i = 0; i < seansOnDay.length; i++) {
                    sessionDTO = new SessionDTO();
                    sessionDTO.setDateOfSeance(seansOnDay[i]);
                    sessionDTO.setFilm(filmDTO);
                    sessionDTO.setHall(hallDTO);
                    sessionDTO.setPrice(price);
                    SessionServiceImpl.getInstance().save(sessionDTO);
                    seansOnDay[i] = seansOnDay[i].plusDays(1);
                }
                beginDate = beginDate.plusDays(1);
            } while (!beginDate.isAfter(endDate));

            request.getRequestDispatcher("../pages/admin/select.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            fieldErr(request, response, "Неверно задана цена.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void fieldErr(HttpServletRequest request, HttpServletResponse response, String o) throws IOException {
        request.getSession().setAttribute("message", o);
        response.sendRedirect(request.getContextPath() + "/admin?select=sessionprepare");
    }
}
