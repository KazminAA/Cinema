package dao.impl;


import models.Hall;
import models.Structure;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 15.09.2016.
 */
public final class HallDaoImpl extends CrudDao<Hall> {
    private static HallDaoImpl hallDao;

    private final String INSERT_HALL = "INSERT INTO hall (name) VALUES (?)";
    private final String UPDATE_HALL = "UPDATE hall SET name=? WHERE id=?";

    private HallDaoImpl(Class<Hall> type) {
        super(type);
    }

    public static synchronized HallDaoImpl getInstance() {
        if (hallDao == null) {
            hallDao = new HallDaoImpl(Hall.class);
        }
        return hallDao;
    }

    @Override
    public List<Hall> readAll(ResultSet resultSet) throws SQLException {
        List<Hall> result = new LinkedList<>();
        while (resultSet.next()) {
            Hall hall = new Hall();
            hall.setId(resultSet.getInt("id"));
            hall.setName(resultSet.getString("name"));
            Structure structure = StructureDao.getInstance().getBy("hallID", Integer.valueOf(hall.getId()).toString()).get(0);
            hall.setStructure(structure.getStructure());
            result.add(hall);
        }
        return result;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HALL, Statement.RETURN_GENERATED_KEYS);
        prepareCommon(entity, preparedStatement);
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HALL);
        prepareCommon(entity, preparedStatement);
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }

    private void prepareCommon(Hall entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    public void save(Hall entity) {
        super.save(entity);
        Structure structure = new Structure(entity.getStructure(), entity.getId());
        StructureDao.getInstance().save(structure);
    }

    @Override
    public void update(Hall entity) {
        super.update(entity);
        Structure structure = new Structure(entity.getStructure(), entity.getId());
        StructureDao.getInstance().update(structure);
    }
}
