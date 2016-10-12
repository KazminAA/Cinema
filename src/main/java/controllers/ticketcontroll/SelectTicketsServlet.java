package controllers.ticketcontroll;

import dto.SessionDTO;
import dto.TicketDTO;
import helpers.SeatHolder;
import service.impl.SessionServiceImpl;
import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by lex on 12.10.16.
 */
@WebServlet(name = "SelectTicketsServlet", urlPatterns = "/selecttickets")
public class SelectTicketsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sessId = 0;
        SessionDTO sessionDTO = null;
        List<SessionDTO> dtoList;
        try {
            sessId = Integer.parseInt(request.getParameter("selecteds"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Ошибка при выборе сеанса.");
            response.sendRedirect(request.getSession().getAttribute("url").toString());
        }
        if ((dtoList = (List<SessionDTO>) request.getSession().getAttribute("sessionsDTO")) == null) {
            sessionDTO = SessionServiceImpl.getInstance().getById(sessId);
        } else {
            for (SessionDTO dto : dtoList) {
                if (dto.getId() == sessId) {
                    sessionDTO = dto;
                    break;
                }
            }
        }
        if (sessionDTO != null) {
            request.setAttribute("sessionDTO", sessionDTO);
            List<TicketDTO> tickets = TicketServiceImpl.getInstance().getSessionTickets(sessionDTO.getId());
            Set<SeatHolder> reservedseat = new HashSet<>();
            for (TicketDTO ticket : tickets) { //make set of reserved seats by exsts tickets
                reservedseat.add(new SeatHolder(ticket.getRaw(), ticket.getCol()));
            }
            int[] struct = sessionDTO.getHall().getStructure();
            int maxcolnumber = 0;
            for (int i = 0; i < struct.length; i++) {
                if (maxcolnumber < struct[i]) {
                    maxcolnumber = struct[i];
                }
            }
            SeatHolder[][] raws = new SeatHolder[struct.length][];//now make struckture of hall where reserved seats will be null
            for (int i = 0; i < struct.length; i++) {
                SeatHolder[] seats = new SeatHolder[maxcolnumber];
                int noseats = (maxcolnumber - struct[i]) / 2;
                for (int j = 0; j < struct[i]; j++) {
                    SeatHolder seat = new SeatHolder(i + 1, j + 1);
                    if (reservedseat.contains(seat)) {
                        seats[j + noseats] = null;
                    } else {
                        seats[j + noseats] = seat;
                    }
                }
               /* for (int j = struct[i]; j < seats.length; j++) {
                    seats[j] = null;
                }*/
                raws[i] = seats;
                System.out.println(Arrays.toString(seats));
            }
            request.setAttribute("freeSeats", raws);
            request.getRequestDispatcher("pages/common/selectseats.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
