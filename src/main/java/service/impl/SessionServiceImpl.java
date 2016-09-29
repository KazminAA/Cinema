package service.impl;


import dao.impl.SessionDaoImpl;
import dto.SessionDTO;
import mappers.BeanMapper;
import models.Session;
import service.api.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr on 19.09.2016.
 */
public class SessionServiceImpl implements Service<SessionDTO> {
    private static SessionServiceImpl sessionService;

    private SessionDaoImpl sessionDao;
    private BeanMapper beanMapper;

    private SessionServiceImpl() {
        this.sessionDao = SessionDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized SessionServiceImpl getInstance() {
        if (sessionService == null) {
            sessionService = new SessionServiceImpl();
        }
        return sessionService;
    }

    @Override
    public List<SessionDTO> getAll() {
        List<Session> sessionList = sessionDao.getAll();
        List<SessionDTO> sessionDTOs = beanMapper.listMapToList(sessionList, SessionDTO.class);
        return sessionDTOs;
    }

    @Override
    public SessionDTO getById(int id) {
        Session session = sessionDao.getById(id);
        SessionDTO sessionDTO = null;
        if (session != null) {
            sessionDTO = beanMapper.singleMapper(session, SessionDTO.class);
        }
        return sessionDTO;
    }

    @Override
    public void save(SessionDTO entity) {
        Session session = beanMapper.singleMapper(entity, Session.class);
        sessionDao.save(session);
    }

    @Override
    public void delete(int id) {
        sessionDao.delete(id);
    }

    @Override
    public void update(SessionDTO entity) {
        Session session = beanMapper.singleMapper(entity, Session.class);
        sessionDao.update(session);
    }

    public List<SessionDTO> getSessionsByFK(String keyName, String filmID) {
        List<Session> sessions = sessionDao.getBy(keyName, filmID);
        List<SessionDTO> result = beanMapper.listMapToList(sessions, SessionDTO.class);
        return result;
    }

    public List<SessionDTO> getSessionBetween(LocalDateTime begin, LocalDateTime end) {
        List<Session> sessions = SessionDaoImpl.getInstance().getSessionBetween(begin, end);
        List<SessionDTO> result = beanMapper.listMapToList(sessions, SessionDTO.class);
        return result;
    }

    @Override
    public void updateFieldIn(String field, String value, int key) {
        sessionDao.updateFieldIn(field, value, key);
    }
}
