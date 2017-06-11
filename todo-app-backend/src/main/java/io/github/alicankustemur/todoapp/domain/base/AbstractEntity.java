package io.github.alicankustemur.todoapp.domain.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity {
		
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RECORD_CREATE_TIME", length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreateTime;

	@Column(name = "RECORD_UPDATE_TIME", length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordUpdateTime;

	@Column(name = "RECORD_IS_DELETED")
	private Boolean recordIsDeleted;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setRecordCreateTime(Date recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}

	public Date getRecordUpdateTime() {
		return recordUpdateTime;
	}

	public void setRecordUpdateTime(Date recordUpdateTime) {
		this.recordUpdateTime = recordUpdateTime;
	}

	public Boolean getRecordIsDeleted() {
		return recordIsDeleted;
	}

	public void setRecordIsDeleted(Boolean recordIsDeleted) {
		this.recordIsDeleted = recordIsDeleted;
	}

}
