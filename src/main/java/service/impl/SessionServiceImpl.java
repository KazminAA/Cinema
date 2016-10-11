package service.impl;


import dao.impl.SessionDaoImpl;
import dto.FilmDTO;
import dto.HallDTO;
import dto.SessionDTO;
import mappers.BeanMapper;
import models.Session;
import service.api.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    public List<SessionDTO> getAllFull() {
        List<SessionDTO> result = getAll();
        List<FilmDTO> filmDTOs = FilmServiceImpl.getInstance().getAll();
        Map<Integer, FilmDTO> filmDTOMap = new HashMap<>();
        for (FilmDTO filmDTO : filmDTOs) {
            filmDTOMap.put(filmDTO.getId(), filmDTO);
        }
        Map<Integer, HallDTO> hallDTOMap = new HashMap<>();
        List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
        for (HallDTO hallDTO : hallDTOs) {
            hallDTOMap.put(hallDTO.getId(), hallDTO);
        }
        for (SessionDTO sessionDTO : result) {
            sessionDTO.setFilm(filmDTOMap.get(sessionDTO.getFilmAsEntity().getId()));
            sessionDTO.setHall(hallDTOMap.get(sessionDTO.getHallAsEntity().getId()));
        }
        return result;
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

    public List<SessionDTO> getSessionBetweenFull(LocalDateTime begin, LocalDateTime end) {
        List<SessionDTO> result = getSessionBetween(begin, end);
        if (result.size() != 0) {
            List<FilmDTO> filmDTOs = FilmServiceImpl.getInstance().getAll();
            Map<Integer, FilmDTO> filmDTOMap = new HashMap<>();
            for (FilmDTO filmDTO : filmDTOs) {
                filmDTOMap.put(filmDTO.getId(), filmDTO);
            }
            Map<Integer, HallDTO> hallDTOMap = new HashMap<>();
            List<HallDTO> hallDTOs = HallServiceImpl.getInstance().getAll();
            for (HallDTO hallDTO : hallDTOs) {
                hallDTOMap.put(hallDTO.getId(), hallDTO);
            }
            for (SessionDTO sessionDTO : result) {
                sessionDTO.setFilm(filmDTOMap.get(sessionDTO.getFilmAsEntity().getId()));
                sessionDTO.setHall(hallDTOMap.get(sessionDTO.getHallAsEntity().getId()));
            }
        }
        return result;
    }

    @Override
    public void updateFieldIn(String field, String value, int key) {
        sessionDao.updateFieldIn(field, value, key);
    }
}
