package shedulers;

import dao.impl.TicketDaoImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lex on 05.10.16.
 */
public class ResrvCleaner extends TimerTask {
    private TicketDaoImpl ticketDao;
    private int inter;

    {
        try {
            Properties properties = new Properties();
            properties.load(ResrvCleaner.class.getClassLoader().getResourceAsStream("application.properties"));
            inter = Integer.valueOf(properties.getProperty("clearingInterval"));
            ticketDao = TicketDaoImpl.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        LocalDateTime time = LocalDateTime.now();
        time = time.minusHours(inter);
        ticketDao.deleteReservedTicket(time);
    }

    public void main() {
        Timer timer = new Timer();
        TimerTask task = new ResrvCleaner();
        timer.schedule(task, 0, 180000);
    }
}
