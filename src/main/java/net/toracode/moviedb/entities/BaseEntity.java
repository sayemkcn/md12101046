package net.toracode.moviedb.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.hateoas.ResourceSupport;

@MappedSuperclass
public abstract class BaseEntity extends ResourceSupport{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;


	public Date getLastUpdated() {
		return lastUpdated;
	}

	@PreUpdate
	public void setLastUpdated() {
		this.lastUpdated = new Date();
	}

	public Date getCreated() {
		return created;
	}

	@PrePersist
	public void setCreated() {
		this.created = new Date();
	}

	public Long getPrimaryId() {
		return id;
	}

	public void setPrimaryId(Long id) {
		this.id = id;
	}
}
