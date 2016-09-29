package dao.api;


import models.Entity;

import java.util.List;

/**
 * Created by Alexandr on 12.09.2016.
 */
public interface Dao<T extends Entity> {
    List<T> getAll();

    T getById(int id);

    void save(T entity);

    void delete(int id);

    void update(T entity);

    List<T> getBy(String keyFieldName, String key);

    void deleteBy(String keyFieldName, String key);

    void updateFieldIn(String field, String value, int key);
}
