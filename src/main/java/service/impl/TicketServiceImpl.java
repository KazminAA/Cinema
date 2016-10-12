package service.impl;


import dao.DaoFactory;
import dao.api.Dao;
import dto.TicketDTO;
import mappers.BeanMapper;
import models.Ticket;
import service.api.Service;

import java.util.List;

/**
 * Created by Alexandr on 22.09.2016.
 */
public class TicketServiceImpl implements Service<TicketDTO> {
    private static TicketServiceImpl ticketService;

    private BeanMapper beanMapper;
    private Dao<Ticket> ticketDao;

    private TicketServiceImpl() {
        this.beanMapper = BeanMapper.getInstance();
        this.ticketDao = DaoFactory.getInstance().getTicketDao();
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }
        return ticketService;
    }

    @Override
    public List<TicketDTO> getAll() {
        List<Ticket> tickets = ticketDao.getAll();
        List<TicketDTO> ticketDTOs = beanMapper.listMapToList(tickets, TicketDTO.class);
        return ticketDTOs;
    }

    @Override
    public TicketDTO getById(int id) {
        Ticket ticket = ticketDao.getById(id);
        TicketDTO result = null;
        if (ticket != null) {
            result = beanMapper.singleMapper(ticket, TicketDTO.class);
        }
        return result;
    }

    @Override
    public void save(TicketDTO entity) {
        Ticket ticket = beanMapper.singleMapper(entity, Ticket.class);
        ticketDao.save(ticket);
    }

    @Override
    public void delete(int id) {
        ticketDao.delete(id);
    }

    @Override
    public void update(TicketDTO entity) {
        Ticket ticket = beanMapper.singleMapper(entity, Ticket.class);
        ticketDao.update(ticket);
    }

    @Override
    public void updateFieldIn(String field, String value, int key) {
        ticketDao.updateFieldIn(field, value, key);
    }

    public List<TicketDTO> getUserTickets(int userID) {
        List<Ticket> tickets = ticketDao.getBy("userID", Integer.toString(userID));
        List<TicketDTO> result = beanMapper.listMapToList(tickets, TicketDTO.class);
        return result;
    }

    public List<TicketDTO> getSessionTickets(int sessionID) {
        List<Ticket> tickets = ticketDao.getBy("sessionID", Integer.toString(sessionID));
        List<TicketDTO> result = beanMapper.listMapToList(tickets, TicketDTO.class);
        return result;
    }
}
