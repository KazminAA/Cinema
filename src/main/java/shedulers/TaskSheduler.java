package shedulers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by lex on 05.10.16.
 */
@WebListener
public class TaskSheduler implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ResrvCleaner resrvCleaner = new ResrvCleaner();
        resrvCleaner.main();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
