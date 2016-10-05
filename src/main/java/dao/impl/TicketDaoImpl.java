package dao.impl;


import datasource.DataSource;
import models.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 22.09.2016.
 */
public class TicketDaoImpl extends CrudDao<Ticket> {
    private static TicketDaoImpl ticketDao;

    private final String INSERT_TICKET = "INSERT INTO ticket (raw, col, chk, purchase, timecreate, sessionID, " +
            "userID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_TICKET = "UPDATE ticket SET raw=?, col=?, chk=?, purchase=?, timecreate=?, " +
            "sessionID=?, userID=? WHERE id=?";
    private final String DELETE_RESERV = "DELETE FROM ticket WHERE timecreate < ? AND purchase=0";

    private TicketDaoImpl(Class<Ticket> type) {
        super(type);
    }

    public static synchronized TicketDaoImpl getInstance() {
        if (ticketDao == null) {
            ticketDao = new TicketDaoImpl(Ticket.class);
        }
        return ticketDao;
    }

    @Override
    public List<Ticket> readAll(ResultSet resultSet) throws SQLException {
        List<Ticket> result = new LinkedList<>();
        while (resultSet.next()) {
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt("id"));
            ticket.setRaw(resultSet.getInt("raw"));
            ticket.setCol(resultSet.getInt("col"));
            ticket.setChk(resultSet.getBoolean("chk"));
            ticket.setPurchase(resultSet.getBoolean("purchase"));
            ticket.setTimecreate(resultSet.getTimestamp("timecreate").toLocalDateTime());
            ticket.setSessionID(resultSet.getInt("sessionID"));
            ticket.setUserID(resultSet.getInt("userID"));
            result.add(ticket);
        }
        return result;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
        prepareCommon(entity, preparedStatement);
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET);
        prepareCommon(entity, preparedStatement);
        preparedStatement.setInt(8, entity.getId());
        return null;
    }

    private void prepareCommon(Ticket entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getRaw());
        preparedStatement.setInt(2, entity.getCol());
        preparedStatement.setBoolean(3, entity.isChk());
        preparedStatement.setBoolean(4, entity.isPurchase());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(entity.getTimecreate()));
        preparedStatement.setInt(6, entity.getSessionID());
        preparedStatement.setInt(7, entity.getUserID());
    }

    public void deleteReservedTicket(LocalDateTime time) {
        Connection connection = DataSource.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERV)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(time));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
