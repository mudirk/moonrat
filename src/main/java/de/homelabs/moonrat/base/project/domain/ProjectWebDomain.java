package de.homelabs.moonrat.base.project.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "project_webdomain")
public class ProjectWebDomain {
	@Id
	long id;
	String name;
	String protocol;
	@Enumerated(EnumType.STRING)
	ProjectWebDomainStatus status;
	Timestamp created;
	Timestamp modified;
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name="project_id", referencedColumnName="id")
	Project project;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProjectWebDomainStatus getStatus() {
		return status;
	}
	public void setStatus(ProjectWebDomainStatus status) {
		this.status = status;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getModified() {
		return modified;
	}
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
