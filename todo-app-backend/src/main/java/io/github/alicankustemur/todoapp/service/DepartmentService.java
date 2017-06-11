package io.github.alicankustemur.todoapp.service;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.base.BaseService;

public interface DepartmentService extends BaseService<Department, Long> {

	public Department getDepartmentByEmployee(Employee employee);

	public boolean isItAvailableDepartmentWithThisEmployee(Employee employee);
}
