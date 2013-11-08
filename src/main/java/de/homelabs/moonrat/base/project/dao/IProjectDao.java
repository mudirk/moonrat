package de.homelabs.moonrat.base.project.dao;

import java.util.List;

import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectStatus;

public interface IProjectDao {
	List<Project> getAllProjects();
	List<Project> getProjectsByStatus(ProjectStatus status);
}
