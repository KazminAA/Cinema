package dao;


import dao.api.Dao;
import dao.impl.*;
import helpers.Container;
import helpers.PropertyHolder;
import models.*;

/**
 * Created by lex on 12.09.16.
 */
public class DaoFactory {
    private static DaoFactory daoFactory;
    private Dao<Film> filmDao;
    private Dao<Hall> hallDao;
    private Dao<Session> sessionDao;
    private Dao<User> userDao;
    private Dao<Ticket> ticketDao;

    private DaoFactory() {
        loadDaos();
    }

    public static synchronized DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    private void loadDaos() {
        if (PropertyHolder.getInstance().isInMemoryDb()) {
            filmDao = new Container<>(1);
        } else {
            filmDao = FilmDaoImpl.getInstance();
            hallDao = HallDaoImpl.getInstance();
            sessionDao = SessionDaoImpl.getInstance();
            userDao = UserDaoImpl.getInstance();
            ticketDao = TicketDaoImpl.getInstance();
        }
    }

    public Dao<Film> getFilmDao() {
        return filmDao;
    }

    public Dao<Hall> getHallDao() {
        return hallDao;
    }

    public Dao<Session> getSessionDao() {
        return sessionDao;
    }

    public Dao<User> getUserDao() {
        return userDao;
    }

    public Dao<Ticket> getTicketDao() {
        return ticketDao;
    }
}
