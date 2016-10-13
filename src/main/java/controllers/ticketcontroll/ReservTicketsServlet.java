package controllers.ticketcontroll;

import dto.TicketDTO;
import dto.UserDTO;
import service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lex on 12.10.16.
 */
@WebServlet(name = "ReservTicketsServlet", urlPatterns = "/reservtickets")
public class ReservTicketsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> param = request.getParameterNames();
        Pattern pattern = Pattern.compile("sess(\\d+)raw(\\d+)");
        Matcher matcher;
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        int userID = ((UserDTO) request.getSession().getAttribute("user")).getId();
        while (param.hasMoreElements()) {
            String name = param.nextElement();
            matcher = pattern.matcher(name);
            if (matcher.find()) {
                String[] cols = request.getParameterValues(name);
                for (int i = 0; i < cols.length; i++) {
                    TicketDTO ticketDTO = new TicketDTO();
                    ticketDTO.setChk(false);
                    ticketDTO.setPurchase(false);
                    ticketDTO.setTimecreate(LocalDateTime.now());
                    ticketDTO.setUserID(userID);
                    ticketDTO.setSessionID(Integer.parseInt(matcher.group(1)));
                    ticketDTO.setRaw(Integer.parseInt(matcher.group(2)));
                    ticketDTO.setCol(Integer.parseInt(cols[i]));
                    ticketService.save(ticketDTO);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/personalarea");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
