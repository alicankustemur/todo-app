package io.github.alicankustemur.todoapp.controller.base;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by alicankustemur on 12/06/2017.
 */
public interface BaseController<T extends AbstractEntity> {

    public ResponseEntity<T> add(T t);

    public List<T> list();

    public ResponseEntity<T> delete(Long id);

    public ResponseEntity<T> update(Long id, T t);

}
