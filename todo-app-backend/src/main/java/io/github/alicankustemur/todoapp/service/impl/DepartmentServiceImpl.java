package io.github.alicankustemur.todoapp.service.impl;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.repository.DepartmentRepository;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.base.AbstractBaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DepartmentServiceImpl extends AbstractBaseServiceImpl<Department, Long> implements DepartmentService {

	DepartmentRepository repository;

	public DepartmentServiceImpl(DepartmentRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Department getDepartmentByEmployee(Employee employee) {
		return repository.findByEmployee(employee);
	}

	@Override
	public boolean isItAvailableDepartmentWithThisEmployee(Employee employee) {
		Department department = repository.findByEmployee(employee);

		if (department != null) {
			return true;
		}
		
		return false;
	}

}
