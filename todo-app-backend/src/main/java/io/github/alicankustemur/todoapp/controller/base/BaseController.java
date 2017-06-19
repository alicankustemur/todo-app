package io.github.alicankustemur.todoapp.controller.base;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by alicankustemur on 12/06/2017.
 */
public interface BaseController<T extends AbstractEntity> {

    public ResponseEntity<?> add(T t);

    public List<T> list();

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> update(Long id, T t);

}
