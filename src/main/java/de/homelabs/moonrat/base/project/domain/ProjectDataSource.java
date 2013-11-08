package de.homelabs.moonrat.base.project.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="project_datasource")
public class ProjectDataSource {
	@Id
	long id;
	@Enumerated(EnumType.STRING)
	ProjectDataSourceType type; 
	String url;
	String username;
	String password;
	
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name="project_id", referencedColumnName="id")
	Project project;

	//getter / setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ProjectDataSourceType getType() {
		return type;
	}
	public void setType(ProjectDataSourceType type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
