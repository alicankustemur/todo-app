package io.github.alicankustemur.todoapp.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;
import io.github.alicankustemur.todoapp.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractBaseServiceImpl<T extends AbstractEntity, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    public AbstractBaseServiceImpl(BaseRepository<T, ID> baseRepository) {
        super();
        this.baseRepository = baseRepository;
    }

    @Override
    public Optional<T> saveOrUpdate(T entity) {
        return Optional.of(baseRepository.save(entity));
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public T get(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public void remove(ID id) {
        baseRepository.delete(id);
    }

    public BaseRepository<T, ID> getBaseRepository() {
        return baseRepository;
    }

    public void setBaseRepository(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }


}
