package service.api;


import models.Entity;

import java.util.List;

/**
 * Created by Alexandr on 12.09.2016.
 */
public interface Service<T extends Entity> {
    List<T> getAll();

    T getById(int id);

    void save(T entity);

    void delete(int id);

    void update(T entity);

}
