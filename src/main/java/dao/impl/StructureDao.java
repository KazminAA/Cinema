package dao.impl;


import datasource.DataSource;
import models.Structure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 16.09.2016.
 */
public final class StructureDao extends CrudDao<Structure> {
    private static StructureDao structureDao;
    private final String INSERT_STRUCTURE_FOR_HALL = "INSERT INTO structure (raw, columns, hallID) " +
            "VALUES (?, ?, ?)";

    private StructureDao(Class<Structure> type) {
        super(type);
    }

    public static synchronized StructureDao getInstance() {
        if (structureDao == null) {
            structureDao = new StructureDao(Structure.class);
        }
        return structureDao;
    }

    @Override
    public void save(Structure entity) {
        int[] columns = entity.getStructure();
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STRUCTURE_FOR_HALL)) {
            preparedStatement.setInt(3, entity.getHalllD());
            for (int i = 0; i < columns.length; i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setInt(2, columns[i]);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Structure> readAll(ResultSet resultSet) throws SQLException {
        List<Structure> result = new LinkedList<>();
        Structure structure = new Structure();
        List<Integer> columns = new ArrayList<>();
        while (resultSet.next()) {
            columns.add(resultSet.getInt("columns"));
            structure.setHalllD(resultSet.getInt("hallID"));
        }
        int[] colArray = new int[columns.size()];
        for (int i = 0; i < colArray.length; i++) {
            colArray[i] = columns.get(i);
        }
        structure.setStructure(colArray);
        result.add(structure);
        return result;
    }

    @Override
    public void update(Structure entity) {
        deleteBy("hallID", Integer.valueOf(entity.getHalllD()).toString());
        this.save(entity);
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Structure entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, Structure entity) throws SQLException {
        return null;
    }
}
