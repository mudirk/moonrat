package de.homelabs.moonrat.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import de.homelabs.moonrat.base.project.domain.ProjectSessionEntity;
import de.homelabs.moonrat.project.dao.SettingsDao;
import de.homelabs.moonrat.project.domain.Settings;

@Controller
@SessionAttributes({ "projectId" })
// attributes from session (session.getAttribute("object")
public class IndexController {

	@Autowired
	SettingsDao settingsDao;
	
	@Autowired
	ProjectSessionEntity entity;
	
	@RequestMapping({"/", "/*", "/*/**"})
	public ModelAndView handleIndex(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("projectId") long projectId) {
		
		List<Settings> settings = settingsDao.getSettings();
		System.out.println(request.getPathInfo());
		System.out.println(request.getPathTranslated());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		model.put("domain", projectId);
		model.put("settings", settings.get(0).getName());
		model.put("created", entity.getCreated().toLocaleString());
		model.put("sessionid", RequestContextHolder.currentRequestAttributes().getSessionId());
		return new ModelAndView("index", model);
	}
}
