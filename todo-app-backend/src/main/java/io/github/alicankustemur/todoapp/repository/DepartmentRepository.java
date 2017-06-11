package io.github.alicankustemur.todoapp.repository;

import io.github.alicankustemur.todoapp.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long> {
	public Department findByEmployee(Employee employee);
}
