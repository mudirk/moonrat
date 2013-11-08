package de.homelabs.moonrat.base.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.homelabs.moonrat.base.project.dao.IProjectDao;
import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectStatus;

@Service
@Transactional("coreTransactionManager")
public class ProjectService implements IProjectService {

	@Autowired
	IProjectDao projectDao;
	
	@Override
	public List<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		List<Project> projects = getProjectsByStatus(ProjectStatus.ACTIVE);
		return projects;
	}
	
	@Override
	public List<Project> getProjectsByStatus(ProjectStatus status) {
		return projectDao.getProjectsByStatus(status);
	}
}
