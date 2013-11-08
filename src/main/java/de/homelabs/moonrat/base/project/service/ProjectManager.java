package de.homelabs.moonrat.base.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.homelabs.moonrat.base.project.domain.Project;
import de.homelabs.moonrat.base.project.domain.ProjectWebDomain;
import de.homelabs.moonrat.base.project.domain.ProjectWebDomainStatus;

@Service("projectManager")
public class ProjectManager {

	@Autowired
	IProjectService projectService;
	
	private List<Project> projects = new ArrayList<Project>();
	private boolean projectsLoaded = false;
	private Map<Long, String> permittedDomains = new HashMap<Long, String>();
	
	//getter / setter
	public List<Project> getProjects() {
		
		//check if project infos are loaded
		if (!projectsLoaded){
			this.loadActiveProjects(); 
			//projectsLoaded = true;
		}
		
		//return
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public void addProject(Project project){
		this.projects.add(project);
	}
	
	public boolean removeProject(Project project){
		if (!this.projects.contains(project)){
			return false;
		} else {
			this.projects.remove(project);
			return true;
		}
	}

	public long isWebDomainPermitted(String domain){
		//iterate over map
		for (long key : permittedDomains.keySet()){
			if (permittedDomains.get(key).equalsIgnoreCase(domain)){
				return key;
			}
		}
		
		//not found
		return 0;
	}
	
	/**
	 * get all active projects
	 */
	private void loadActiveProjects(){
		this.projects = projectService.getAllActiveProjects();
		//extract permitted domains
		extractPermittedDomainsFromProjects();
	}
	
	/**
	 * extract permitted domains
	 */
	private void extractPermittedDomainsFromProjects(){
		for (Project project : projects){
			for (ProjectWebDomain webDomain : project.getProjectWebDomains()){
				if (webDomain.getStatus() == ProjectWebDomainStatus.ACTIVE){
					//only active domains
					permittedDomains.put(project.getId(), webDomain.getName());
				}
			}
		}
	}
}
