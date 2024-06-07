package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.service.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T update(ID id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        } else {
            throw new DataNotFoundedException(entity.getClass() + " with " + id + " not found");
        }
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
