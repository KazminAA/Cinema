package service.impl;


import dao.DaoFactory;
import dao.api.Dao;
import dto.FilmDTO;
import mappers.BeanMapper;
import models.Film;
import service.api.Service;

import java.util.List;

/**
 * Created by lex on 13.09.16.
 */
public final class FilmServiceImpl implements Service<FilmDTO> {
    private static FilmServiceImpl filmService;
    private Dao<Film> filmDao;
    private BeanMapper beanMapper;

    private FilmServiceImpl() {
        filmDao = DaoFactory.getInstance().getFilmDao();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized FilmServiceImpl getInstance() {
        if (filmService == null) {
            filmService = new FilmServiceImpl();
        }
        return filmService;
    }

    @Override
    public List<FilmDTO> getAll() {
        List<Film> films = filmDao.getAll();
        List<FilmDTO> filmsDTO = beanMapper.listMapToList(films, FilmDTO.class);
        return filmsDTO;
    }

    @Override
    public FilmDTO getById(int id) {
        Film film = filmDao.getById(id);
        FilmDTO filmDTO = null;
        if (film != null) {
            filmDTO = beanMapper.singleMapper(film, FilmDTO.class);
        }
        return filmDTO;
    }

    @Override
    public void save(FilmDTO entity) {
        Film film = beanMapper.singleMapper(entity, Film.class);
        filmDao.save(film);
    }

    @Override
    public void delete(int id) {
        filmDao.delete(id);
    }

    @Override
    public void update(FilmDTO entity) {
        Film film = beanMapper.singleMapper(entity, Film.class);
        filmDao.update(film);
    }
}
