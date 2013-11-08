package de.homelabs.moonrat.base.project.service;

import java.util.List;

import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectStatus;

public interface IProjectService {
	/**
	 * get all projects
	 * 
	 * @return List<Project> - List of Projects 
	 */
	List<Project> getAllProjects();
	
	/**
	 * get all project with status ProjectStatus.ACTIVE
	 * @return List<Project> - List of projects
	 */
	List<Project> getAllActiveProjects();
	
	/**
	 * get projects by project-status
	 * @see ProjectStatus
	 * @param status - Status to check
	 * @return List<Project> - List of Projects
	 */
	List<Project> getProjectsByStatus(ProjectStatus status);
}
