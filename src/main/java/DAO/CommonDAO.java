package DAO;

import java.util.List;

public interface CommonDAO<T> {
    List<T> getAll();

    Integer addObj(T obj);

    T getById(int id);

    void update(T obj);

    void delete(int id);
}
