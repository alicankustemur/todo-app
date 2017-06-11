package io.github.alicankustemur.todoapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;

@Entity
@Table(name = "DEPARTMENT")
@Where(clause = "RECORD_IS_DELETED = 0")
public class Department extends AbstractEntity implements Serializable {
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE")
	private Employee employee;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
