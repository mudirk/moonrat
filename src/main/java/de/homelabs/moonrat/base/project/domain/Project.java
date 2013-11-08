package de.homelabs.moonrat.base.project.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(schema="public", name="project")
public class Project {
	@Id
	long id;
	String name;
	@Enumerated(EnumType.STRING)
	ProjectStatus status;
	Timestamp created;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=ProjectWebDomain.class, mappedBy="project")
	List<ProjectWebDomain> projectWebDomains;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity= ProjectDataSource.class, mappedBy="project")
	List<ProjectDataSource> projectDataSource;
	
	//getter / setter
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
	public ProjectStatus getStatus() {
		return status;
	}
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public List<ProjectDataSource> getProjectDataSource() {
		return projectDataSource;
	}
	public void setProjectDataSource(List<ProjectDataSource> projectDataSource) {
		this.projectDataSource = projectDataSource;
	}
	public List<ProjectWebDomain> getProjectWebDomains() {
		return projectWebDomains;
	}
	public void setProjectWebDomains(List<ProjectWebDomain> projectWebDomains) {
		this.projectWebDomains = projectWebDomains;
	}
}
