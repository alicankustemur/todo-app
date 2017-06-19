package io.github.alicankustemur.todoapp.controller;

import io.github.alicankustemur.todoapp.controller.base.AbstractBaseController;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends AbstractBaseController<Employee, EmployeeService> {

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody Employee employee) {

        if (service.isNotItAvailableByIdentity(employee.getIdentity())) {
            return ResponseEntity.ok(service.saveOrUpdate(employee));
        }

        return ResponseEntity.badRequest().body("employeeAvailableError");
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Employee employee) {

        Employee willBeUpdatedEmployee = service.get(id);
        if (employee.getIdentity().equals(willBeUpdatedEmployee.getIdentity()) || service.isNotItAvailableByIdentity(employee.getIdentity())) {
            willBeUpdatedEmployee.setIdentity(employee.getIdentity());
            willBeUpdatedEmployee.setName(employee.getName());
            willBeUpdatedEmployee.setSalary(employee.getSalary());

            return ResponseEntity.ok(service.saveOrUpdate(willBeUpdatedEmployee));
        }

        return ResponseEntity.badRequest().body("employeeAvailableError");
    }

}
