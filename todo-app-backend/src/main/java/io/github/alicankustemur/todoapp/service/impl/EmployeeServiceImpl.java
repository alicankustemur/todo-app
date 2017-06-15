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

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean isNotItAvailableByIdentity(Long identity) {
        if (repository.findByIdentity(identity) == null) {
            return true;
        }

        return false;
    }
}
