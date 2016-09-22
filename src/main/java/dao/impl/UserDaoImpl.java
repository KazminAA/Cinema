package dao.impl;


import datasource.DataSource;
import models.User;
import models.UserRole;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 20.09.2016.
 */
public class UserDaoImpl extends CrudDao<User> {
    private static UserDaoImpl userDao;

    private final String INSERT_USER = "INSERT INTO user (login, passwd, email, name, surname, birthday, roleID, sex) " +
            "VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
    private final String UPDATE_USER = "UPDATE user SET login=?, passwd=?, email=?, name=?, surname=?, birthday=?, " +
            "roleID=?, sex=? WHERE id=?";
    private final String CHECK_USER = "SELECT * FROM user WHERE login=? AND passwd=?";

    private UserDaoImpl(Class<User> type) {
        super(type);
    }

    public static synchronized UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl(User.class);
        }
        return userDao;
    }

    @Override
    public List<User> readAll(ResultSet resultSet) throws SQLException {
        List<User> result = new LinkedList<>();
        while (resultSet.next()) {
            User u = new User();
            u.setId(resultSet.getInt("id"));
            u.setLogin(resultSet.getString("login"));
            u.setPwd(resultSet.getString("passwd"));
            u.setEmail(resultSet.getString("email"));
            u.setUserName(resultSet.getString("name"));
            u.setUserSurname(resultSet.getString("surname"));
            u.setBirthday(resultSet.getDate("birthday").toLocalDate());
            UserRole role;
            role = RoleDaoImpl.getInstance().getById(resultSet.getInt("roleID"));
            u.setRole(role);
            u.setSex(resultSet.getBoolean("sex"));
            result.add(u);
        }
        return result;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        prepareCommon(entity, preparedStatement);
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        prepareCommon(entity, preparedStatement);
        preparedStatement.setInt(9, entity.getId());
        return preparedStatement;
    }

    private void prepareCommon(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPwd());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getUserName());
        preparedStatement.setString(5, entity.getUserSurname());
        preparedStatement.setDate(6, Date.valueOf(entity.getBirthday()));
        preparedStatement.setInt(7, entity.getRole().getId());
        preparedStatement.setBoolean(8, entity.getSex());
    }

    public boolean checkUser(String name, String pwd) {
        boolean result = false;
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
