package de.homelabs.moonrat.base.filter;

import java.io.IOException;
import java.net.URL;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.GenericFilterBean;

import de.homelabs.moonrat.base.project.domain.ProjectSessionEntity;
import de.homelabs.moonrat.base.project.service.ProjectManager;

@Component
public class DomainFilter extends GenericFilterBean implements ApplicationContextAware {

	@Autowired
	ProjectManager projectManager;
	
	ApplicationContext context;
	
	public DomainFilter(){
		System.out.println("Domain Filter created");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter");
		//RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes attributes = new ServletRequestAttributes((HttpServletRequest) request);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String webdomain = new URL(httpRequest.getRequestURL().toString()).getHost();
		
		//check domain, returns the related projectId
		//projectManager = (ProjectManager) context.getBean("projectManager");
		long projectId = projectManager.isWebDomainPermitted(webdomain);
		
		if (projectId == 0){
			//TODO: not permitted
			response.getWriter().println("domain is not permitted!");
			System.out.println("domain is not permitted!");
		} else {
			attributes.setAttribute("projectId", projectId, RequestAttributes.SCOPE_SESSION);
			RequestContextHolder.setRequestAttributes(attributes);
			ProjectSessionEntity entity = (ProjectSessionEntity) context.getBean("projectSessionEntity");
			entity.setId(projectId);
			//	next filter element
			chain.doFilter(request, response);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}

}
