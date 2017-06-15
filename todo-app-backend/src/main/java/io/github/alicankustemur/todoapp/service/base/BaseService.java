package io.github.alicankustemur.todoapp.service.base;

import java.util.List;
import java.util.Optional;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;

public interface BaseService<T extends AbstractEntity, ID> {

	public T saveOrUpdate(T entity);

	public List<T> getAll();

	public T get(ID id);

	public void remove(ID id);

}
