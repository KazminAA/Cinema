package controllers.sessioncontroll;

import dto.FilmDTO;
import dto.HallDTO;
import helpers.PropertyHolder;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr on 07.10.2016.
 */
@WebServlet(name = "DataCalcForSessServlet", urlPatterns = "/admin/datacalc")
public class DataCalcForSessServlet extends HttpServlet {

    private int sessBreak;
    private int duration;
    private Map<LocalTime, LocalTime> sessTimes;

    {
        sessBreak = PropertyHolder.getInstance().getSessBreakeMin();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        FilmDTO filmDTO = null;
        List<FilmDTO> filmDTOs;
        HallDTO hallDTO;
        List<HallDTO> hallDTOs;

        if (request.getParameter("beginTime").equals("")) {
            fieldErr(request, response, "Не задано начало сеансов.");
            return;
        }

        if (request.getParameter("endTime").equals("")) {
            fieldErr(request, response, "Не задан конец сеансов.");
            return;
        }

        if ((filmDTOs = (List<FilmDTO>) request.getSession().getAttribute("films")) == null) {
            filmDTOs = FilmServiceImpl.getInstance().getAll();
        }
        for (FilmDTO film : filmDTOs) {
            if (film.getId() == Integer.parseInt(request.getParameter("filmID"))) {
                filmDTO = film;
                request.getSession().setAttribute("film", filmDTO);
                break;
            }
        }
        if ((hallDTOs = (List<HallDTO>) request.getSession().getAttribute("halls")) == null) {
            hallDTOs = HallServiceImpl.getInstance().getAll();
        }
        for (HallDTO hall : hallDTOs) {
            if (hall.getId() == Integer.parseInt(request.getParameter("hallID"))) {
                hallDTO = hall;
                request.getSession().setAttribute("hall", hallDTO);
                break;
            }
        }

        LocalTime beginTime = LocalTime.parse(request.getParameter("beginTime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));

        LocalDateTime beginDateTime = LocalDateTime.of(2000, 12, 10, beginTime.getHour(), beginTime.getMinute());
        LocalDateTime endDateTime = LocalDateTime.of(2000, 12, 10, endTime.getHour(), endTime.getMinute());
        int count = 0;
        duration = filmDTO.getDurationMin();
        sessTimes = new LinkedHashMap<>();

        if (beginDateTime.isAfter(endDateTime)) {
            endDateTime = endDateTime.plusDays(1);
        }
        while (beginDateTime.isBefore(endDateTime)) {
            beginDateTime = getNewBeginTime(beginDateTime);
            count++;
        }
        request.setAttribute("seansTimeCount", count);
        request.setAttribute("sessTimes", sessTimes);
        request.setAttribute("price", request.getParameter("price"));
        request.getRequestDispatcher("../pages/admin/sessiontimes.jsp").forward(request, response);
    }

    private void fieldErr(HttpServletRequest request, HttpServletResponse response, String o) throws IOException {
        request.getSession().setAttribute("message", o);
        response.sendRedirect(request.getContextPath() + "/admin?select=sessionprepare");
    }

    private LocalDateTime getNewBeginTime(LocalDateTime beginTime) {
        sessTimes.put(beginTime.toLocalTime(), beginTime.plusMinutes(duration).toLocalTime());
        beginTime = beginTime.plusMinutes(duration + sessBreak);
        return beginTime;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
