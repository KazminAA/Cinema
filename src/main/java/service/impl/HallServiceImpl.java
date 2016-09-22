package service.impl;


import dao.DaoFactory;
import dao.api.Dao;
import dto.HallDTO;
import mappers.BeanMapper;
import models.Hall;
import service.api.Service;

import java.util.List;

/**
 * Created by lex on 15.09.16.
 */
public final class HallServiceImpl implements Service<HallDTO> {
    private static HallServiceImpl hallService;
    private Dao<Hall> hallDao;
    private BeanMapper beanMapper;
    private HallDTO hallDTO;

    private HallServiceImpl() {
        hallDao = DaoFactory.getInstance().getHallDao();
        beanMapper = BeanMapper.getInstance();
    }

    public static HallServiceImpl getInstance() {
        if (hallService == null) {
            hallService = new HallServiceImpl();
        }
        return hallService;
    }

    @Override
    public List<HallDTO> getAll() {
        List<Hall> halls = hallDao.getAll();
        List<HallDTO> hallDTOs = beanMapper.listMapToList(halls, HallDTO.class);
        return hallDTOs;
    }

    @Override
    public HallDTO getById(int id) {
        Hall hall = hallDao.getById(id);
        HallDTO hallDTO = null;
        if (hall != null) {
            hallDTO = beanMapper.singleMapper(hall, HallDTO.class);
        }
        return hallDTO;
    }

    @Override
    public void save(HallDTO entity) {
        Hall hall = beanMapper.singleMapper(entity, Hall.class);
        hallDao.save(hall);
    }

    @Override
    public void delete(int id) {
        hallDao.delete(id);
    }

    @Override
    public void update(HallDTO entity) {
        Hall hall = beanMapper.singleMapper(entity, Hall.class);
        hallDao.update(hall);
    }
}
