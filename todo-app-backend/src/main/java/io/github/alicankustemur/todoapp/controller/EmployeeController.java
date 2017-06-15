package io.github.alicankustemur.todoapp.controller;

import java.util.Date;
import java.util.List;

import io.github.alicankustemur.todoapp.controller.base.BaseController;
import io.github.alicankustemur.todoapp.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import io.github.alicankustemur.todoapp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController implements BaseController<Employee> {

    @Autowired
    private EmployeeService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {

        if(service.isNotItAvailableByIdentity(employee.getIdentity())){
            employee.setRecordIsDeleted(false);
            employee.setRecordCreateTime(new Date());
            return ResponseEntity.ok(service.saveOrUpdate(employee));
        }

        return ResponseEntity.badRequest().body(employee);
    }

    @GetMapping("/list")
    @Override
    public List<Employee> list() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id) {
        Employee deletedEmployee = service.get(id);
        deletedEmployee.setRecordIsDeleted(true);
        deletedEmployee.setRecordUpdateTime(new Date());

        return ResponseEntity.ok(service.saveOrUpdate(deletedEmployee));
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee willBeUpdatedEmployee = service.get(id);

        willBeUpdatedEmployee.setName(employee.getName());
        willBeUpdatedEmployee.setSurname(employee.getSurname());
        willBeUpdatedEmployee.setSalary(employee.getSalary());
        willBeUpdatedEmployee.setRecordUpdateTime(new Date());

        return ResponseEntity.ok(service.saveOrUpdate(willBeUpdatedEmployee));
    }

}
