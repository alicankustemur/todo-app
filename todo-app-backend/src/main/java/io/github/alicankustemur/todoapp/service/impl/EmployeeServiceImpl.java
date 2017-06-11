package io.github.alicankustemur.todoapp.service.impl;

import javax.transaction.Transactional;

import io.github.alicankustemur.todoapp.domain.Employee;
import org.springframework.stereotype.Service;

import io.github.alicankustemur.todoapp.repository.EmployeeRepository;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import io.github.alicankustemur.todoapp.service.base.AbstractBaseServiceImpl;

@Transactional
@Service
public class EmployeeServiceImpl extends AbstractBaseServiceImpl<Employee, Long> implements EmployeeService {

	public EmployeeServiceImpl(EmployeeRepository repository) {
		super(repository);
	}

}
