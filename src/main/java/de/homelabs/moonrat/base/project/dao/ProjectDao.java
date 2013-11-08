package de.homelabs.moonrat.base.project.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import de.homelabs.collection.CollectionUtils;
import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectStatus;

@Repository
public class ProjectDao implements IProjectDao {

	@Autowired
	@Qualifier("coreSessionFactoryBean")
	SessionFactory sessionFactory;

	@Override
	public List<Project> getAllProjects() {
		return CollectionUtils.convertToCheckedList(Project.class,
				sessionFactory.getCurrentSession().createQuery("from Project")
						.list());
	}

	@Override
	public List<Project> getProjectsByStatus(ProjectStatus status) {
		return CollectionUtils.convertToCheckedList(Project.class, 
				sessionFactory.getCurrentSession().createQuery("from Project where status = :status").setParameter("status", status).list());
	}
}
