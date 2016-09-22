package dao.impl;


import datasource.DataSource;
import helpers.ValueHolder;
import models.Session;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 19.09.2016.
 */
public class SessionDaoImpl extends CrudDao<Session> {
    private static SessionDaoImpl sessionDao;

    private final String INSERT_SESSION = "INSERT INTO session (filmID, datetime, hallID, price) " +
            "VALUES (?, ?, ?, ?)";
    private final String UPDATE_SESSION = "UPDATE session SET filmID=?, datetime=?, hallID=?, " +
            "price=? WHERE id=?";
    private final String SELECT_SESSION_BETWEEN = "SELECT * FROM session WHERE datetime BETWEEN ? AND ?";

    private SessionDaoImpl(Class<Session> type) {
        super(type);
    }

    public static synchronized SessionDaoImpl getInstance() {
        if (sessionDao == null) {
            sessionDao = new SessionDaoImpl(Session.class);
        }
        return sessionDao;
    }

    @Override
    public List<Session> readAll(ResultSet resultSet) throws SQLException {
        List<Session> sessionList = new LinkedList<>();
        while (resultSet.next()) {
            Session session = new Session();
            session.setId(resultSet.getInt("id"));
            ValueHolder holder = new ValueHolder();
            holder.setId(resultSet.getInt("filmID"));
            session.setFilmAsEntity(holder);
            session.setDateOfSeance(resultSet.getTimestamp("datetime").toLocalDateTime());
            holder = new ValueHolder();
            holder.setId(resultSet.getInt("hallID"));
            session.setHallAsEntity(holder);
            session.setPrice(resultSet.getBigDecimal("price"));
            sessionList.add(session);
        }
        return sessionList;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Session entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION, Statement.RETURN_GENERATED_KEYS);
        prepareCommon(entity, preparedStatement);
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, Session entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SESSION);
        prepareCommon(entity, preparedStatement);
        preparedStatement.setInt(5, entity.getId());
        return preparedStatement;
    }

    private void prepareCommon(Session entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getFilmAsEntity().getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDateOfSeance()));
        preparedStatement.setInt(3, entity.getHallAsEntity().getId());
        preparedStatement.setBigDecimal(4, entity.getPrice());
    }

    public List<Session> getSessionBetween(LocalDateTime begin, LocalDateTime end) {
        List<Session> result = null;
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SESSION_BETWEEN);) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(begin));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
