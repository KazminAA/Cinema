package dao.impl;


import models.Film;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr on 12.09.2016.
 */
public final class FilmDaoImpl extends CrudDao<Film> {
    private static FilmDaoImpl filmDao;
    private final String INSERT = "INSERT INTO film (name, year, duration, genre, country, produser, description, cast, smallposter, bigposter)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE film SET name=?, year=?, duration=?, genre=?, country=?, produser=?, description=?, " +
            "cast=?, smallposter=?, bigposter=? WHERE id=?";

    private FilmDaoImpl(Class<Film> type) {
        super(type);
    }

    public static synchronized FilmDaoImpl getInstance() {
        if (filmDao == null) {
            filmDao = new FilmDaoImpl(Film.class);
        }
        return filmDao;
    }

    @Override
    public List<Film> readAll(ResultSet resultSet) throws SQLException {
        List<Film> result = new LinkedList<>();
        while (resultSet.next()) {
            Film film = new Film();
            film.setId(resultSet.getInt("id"));
            film.setName(resultSet.getString("name"));
            film.setYearOfRelease(resultSet.getInt("year"));
            film.setDurationMin(resultSet.getInt("duration"));
            film.setGenre(resultSet.getString("genre"));
            film.setCountry(resultSet.getString("country"));
            film.setProduser(resultSet.getString("produser"));
            film.setDescription(resultSet.getString("description"));
            film.setCast(resultSet.getString("cast"));
            film.setSmallPoster(resultSet.getString("smallposter"));
            film.setBigPoster(resultSet.getString("bigposter"));
            result.add(film);
        }
        return result;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        prepareCommon(entity, preparedStatement);
        return preparedStatement;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        prepareCommon(entity, preparedStatement);
        preparedStatement.setInt(11, entity.getId());
        return preparedStatement;
    }

    private void prepareCommon(Film entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getYearOfRelease());
        preparedStatement.setInt(3, entity.getDurationMin());
        preparedStatement.setString(4, entity.getGenre());
        preparedStatement.setString(5, entity.getCountry());
        preparedStatement.setString(6, entity.getProduser());
        preparedStatement.setString(7, entity.getDescription());
        preparedStatement.setString(8, entity.getCast());
        preparedStatement.setString(9, entity.getSmallPoster());
        preparedStatement.setString(10, entity.getBigPoster());
    }
}
