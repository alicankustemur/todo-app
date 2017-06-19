package io.github.alicankustemur.todoapp.repository;

import io.github.alicankustemur.todoapp.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import io.github.alicankustemur.todoapp.domain.Employee;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    public Employee findByIdentity(Long identity);

}
