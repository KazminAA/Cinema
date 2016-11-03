package dao.impl;


import models.UserRole;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 21.09.2016.
 */
public class RoleDaoImpl extends CrudDao<UserRole> {
    private static RoleDaoImpl roleDao;

    private final String INSERT_ROLE = "INSERT INTO role (name) VALUES (?)";
    private final String UPDATE_ROLE = "UPDATE role SET name=? WHERE id=?";

    private RoleDaoImpl(Class<UserRole> type) {
        super(type);
    }

    public static synchronized RoleDaoImpl getInstance() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl(UserRole.class);
        }
        return roleDao;
    }

    @Override
    public List<UserRole> readAll(ResultSet resultSet) throws SQLException {
        List<UserRole> result = new LinkedList<>();
        while (resultSet.next()) {
            UserRole role = new UserRole();
            role.setId(resultSet.getInt("id"));
            role.setName(resultSet.getString("name"));
            result.add(role);
        }
        return result;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, UserRole entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, UserRole entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }
}
