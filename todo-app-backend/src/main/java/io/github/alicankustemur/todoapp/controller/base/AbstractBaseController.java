package io.github.alicankustemur.todoapp.controller.base;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;
import io.github.alicankustemur.todoapp.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alicankustemur on 16/06/2017.
 */

public abstract class AbstractBaseController<T extends AbstractEntity, S extends BaseService<T, Long>> implements BaseController<T> {

    private BaseService<T, Long> baseService;

    @Autowired
    protected S service = (S) baseService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody T entity) {
        return ResponseEntity.ok(service.saveOrUpdate(entity));
    }

    @GetMapping("/list")
    @ResponseBody
    public List<T> list() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.remove(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody T entity) {
        T willBeUpdatedEntity = service.get(id);
        entity.setId(willBeUpdatedEntity.getId());
        entity.setRecordCreateTime(willBeUpdatedEntity.getRecordCreateTime());
        return ResponseEntity.ok(service.saveOrUpdate(entity));
    }

}
