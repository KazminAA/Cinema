package service.impl;


import dao.DaoFactory;
import dao.api.Dao;
import dao.impl.UserDaoImpl;
import dto.UserDTO;
import mappers.BeanMapper;
import models.User;
import service.api.Service;

import java.util.List;

/**
 * Created by Alexandr on 20.09.2016.
 */
public class UserServiceImpl implements Service<UserDTO> {
    private static UserServiceImpl userService;
    private Dao<User> userDao;
    private BeanMapper beanMapper;

    private UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().getUserDao();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDao.getAll();
        List<UserDTO> result = beanMapper.listMapToList(users, UserDTO.class);
        return result;
    }

    @Override
    public UserDTO getById(int id) {
        User user = userDao.getById(id);
        UserDTO result = null;
        if (user != null) {
            result = beanMapper.singleMapper(user, UserDTO.class);
        }
        return result;
    }

    @Override
    public void save(UserDTO entity) {
        User user = beanMapper.singleMapper(entity, User.class);
        userDao.save(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(UserDTO entity) {
        User user = beanMapper.singleMapper(entity, User.class);
        userDao.update(user);
    }

    public boolean checkUser(String name, String pwd) {
        return UserDaoImpl.getInstance().checkUser(name, pwd);
    }

    public UserDTO getByLogin(String login) {
        List<User> users = userDao.getBy("login", login);
        UserDTO result = (users.size() > 0) ? beanMapper.singleMapper(users.get(0), UserDTO.class) : null;
        return result;
    }

    @Override
    public void updateFieldIn(String field, String value, int key) {
        userDao.updateFieldIn(field, value, key);
    }
}
