package by.artsem.druzhbahub.service;

import java.util.List;

public interface CRUDService<T, ID> {
    T create(T entity);

    T getById(ID id);

    List<T> getAll();

    T update(ID id, T entity);

    void delete(ID id);
}