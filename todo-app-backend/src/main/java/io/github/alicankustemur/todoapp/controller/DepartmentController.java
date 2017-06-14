package io.github.alicankustemur.todoapp.controller;

import io.github.alicankustemur.todoapp.controller.base.BaseController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController implements BaseController<Department> {

    @Autowired
    private DepartmentService service;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    @Override
    public Department add(@RequestBody Department department) {
        department.setRecordIsDeleted(false);
        department.setRecordCreateTime(new Date());
        return service.saveOrUpdate(department).orElse(null);
    }

    @GetMapping("/list")
    @Override
    public List<Department> list() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(@PathVariable("id") Long id) {
        Department deletedDepartment = service.get(id);
        deletedDepartment.setRecordIsDeleted(true);
        deletedDepartment.setRecordUpdateTime(new Date());

        service.saveOrUpdate(deletedDepartment);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Department department) {
        Department willBeUpdatedDepartment = service.get(id);

        if (service.isItAvailableDepartmentWithThisEmployee(department.getEmployee())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            willBeUpdatedDepartment.setName(department.getName());
            willBeUpdatedDepartment.setDescription(department.getDescription());
            willBeUpdatedDepartment.setEmployee(department.getEmployee());
            willBeUpdatedDepartment.setRecordUpdateTime(new Date());

            service.saveOrUpdate(willBeUpdatedDepartment);

            return ResponseEntity.ok("");
        }
    }
}
