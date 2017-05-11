package dataModel;

import entities.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sasha on 02.05.2017.
 */
public interface GenericDao<T extends Entity, K extends Serializable> {
    T create();
    T persist(T object);
    T getById(long id);
    void update(T object);
    void delete(T object);
    List<T> getAll();
}
