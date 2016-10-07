package controllers.sessioncontroll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Alexandr on 30.09.2016.
 */
@WebServlet(name = "AddSessionServlet", urlPatterns = "/admin/addsession")
public class AddSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        BigDecimal price;
        try {
            if (request.getParameter("beginDate").equals("")) {
                fieldErr(request, response, "Не заполнена начальная дата.");
            }
            if (request.getParameter("endDate").equals("")) {
                fieldErr(request, response, "Не заполнена конечная дата.");
            }
            price = new BigDecimal(request.getParameter("priceChk"));
            String[] times = request.getParameterValues("beginTime");
            LocalDateTime[] seansOnDay = new LocalDateTime[times.length];
            for (int i = 0; i < times.length; i++) {
                seansOnDay[i] = LocalDateTime.parse((request.getParameter("beginDate") + " " +
                        times[i]), dateFormatter);
            }
            int len = seansOnDay.length - 1;
            while (seansOnDay[len].isBefore(seansOnDay[0])) {
                seansOnDay[len] = seansOnDay[len].plusDays(1);
                len--;
            }
            for (LocalDateTime localDateTime : seansOnDay) {
                System.out.println(localDateTime);
            }
            /*FilmDTO filmDTO = null;
            HallDTO hallDTO = null;
            List<FilmDTO> filmDTOs;
            List<HallDTO> hallDTOs;
            if ((filmDTOs = (List<FilmDTO>) request.getSession().getAttribute("films")) == null) {
                filmDTOs = FilmServiceImpl.getInstance().getAll();
            }
            if ((hallDTOs = (List<HallDTO>) request.getSession().getAttribute("halls")) == null) {
                hallDTOs = HallServiceImpl.getInstance().getAll();
            }
            for (FilmDTO film : filmDTOs) {
                if (film.getId() == Integer.parseInt(request.getParameter("filmID"))) {
                    filmDTO = film;
                    break;
                }
            }
            for (HallDTO hall : hallDTOs) {
                if (hall.getId() == Integer.parseInt(request.getParameter("hallID"))) {
                    hallDTO = hall;
                    break;
                }
            }
            SessionDTO sessionDTO = new SessionDTO(filmDTO, LocalDateTime.parse(request.getParameter("dateOfSeance")),
                    hallDTO, price);
            SessionServiceImpl.getInstance().save(sessionDTO);
            request.getRequestDispatcher("../pages/admin/select.jsp").forward(request, response);*/
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
