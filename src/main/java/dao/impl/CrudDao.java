package dao.impl;


import dao.api.Dao;
import datasource.DataSource;
import models.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandr on 12.09.2016.
 */
public abstract class CrudDao<T extends Entity> implements Dao<T> {
    public static final String SELECT_ALL = "SELECT * FROM %s";
    public static final String SELECT_BY_ID = "SELECT * FROM %s WHERE id = ?";
    public static final String SELECT_BY_FOREIGN_KEY = "SELECT * FROM %s WHERE %s=?";
    public static final String DELETE_BY_FOREIGN_KEY = "DELETE FROM %s WHERE %s=?";
    public static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";
    public static final String UPDATE_FIELD = "UPDATE %s SET %s=? WHERE id=?";
    private Class<T> type;
    private DataSource dataSource;

    public CrudDao(Class<T> type) {
        this.type = type;
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<T> getAll() {
        List result = null;
        String sql = String.format(SELECT_ALL, type.getSimpleName().toLowerCase());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T getById(int id) {
        List result = null;
        String sql = String.format(SELECT_BY_ID, type.getSimpleName().toLowerCase());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (result.size() > 0) ? (T) result.get(0) : null;
    }

    @Override
    public List<T> getBy(String keyFieldName, String key) {
        List result = null;
        String sql = String.format(SELECT_BY_FOREIGN_KEY, type.getSimpleName().toLowerCase(), keyFieldName);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(T entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity);
        ) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createUpdateStatement(connection, entity);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = String.format(DELETE_BY_ID, type.getSimpleName().toLowerCase());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBy(String keyFieldName, String key) {
        String sql = String.format(DELETE_BY_FOREIGN_KEY, type.getSimpleName().toLowerCase(), keyFieldName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFieldIn(String field, String value, int key) {
        String sql = String.format(UPDATE_FIELD, type.getSimpleName().toLowerCase(), field);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract List<T> readAll(ResultSet resultSet) throws SQLException;

    public abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException;

    public abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException;
}
