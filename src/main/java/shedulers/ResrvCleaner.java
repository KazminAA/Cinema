package shedulers;

import dao.impl.TicketDaoImpl;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lex on 05.10.16.
 */
public class ResrvCleaner extends TimerTask {
    TicketDaoImpl ticketDao;

    @Override
    public void run() {
        ticketDao = TicketDaoImpl.getInstance();
        LocalDateTime time = LocalDateTime.now();
        time = time.minusHours(3);
        ticketDao.deleteReservedTicket(time);
    }

    public void main() {
        Timer timer = new Timer();
        TimerTask task = new ResrvCleaner();
        timer.schedule(task, 0, 180000);
    }
}
