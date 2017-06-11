package io.github.alicankustemur.todoapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "MEETING")
@Where(clause = "RECORD_IS_DELETED = 0")
public class Meeting extends AbstractEntity {
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT")
	private Department department;
	
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
