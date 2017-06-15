package io.github.alicankustemur.todoapp.service;

import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.base.BaseService;

public interface EmployeeService extends BaseService<Employee, Long> {
    public boolean isNotItAvailableByIdentity(Long identity);

}
